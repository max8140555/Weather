package com.max.android_perona.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.max.android_perona.BuildConfig
import com.max.android_perona.model.GsonParser
import com.max.android_perona.model.JsonParser
import com.max.android_perona.model.cache.CacheDatabase
import com.max.android_perona.model.cache.WeatherSharedPreferences
import com.max.android_perona.model.repository.WeatherRepository
import com.max.android_perona.model.repository.WeatherRepositoryImpl
import com.max.android_perona.model.service.weather.WeatherService
import com.max.android_perona.model.usecase.WeatherUseCase
import com.max.android_perona.model.usecase.WeatherUseCaseImpl
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val WEATHER_BASE_URL = "https://opendata.cwb.gov.tw/api/"
private const val DATABASE_NAME = "app_cache_database"

val appCoreModule = module {
    single { createGson() }
    single { createJsonParser(get()) }
    single { createOkHttpClient(TimeUnit.SECONDS.toMillis(60)) }
    single { provideWeatherService(provideWeatherRetrofit(WEATHER_BASE_URL, get(), get())) }
    single { createRoomDatabase(androidContext()) }
    single { WeatherSharedPreferences(androidContext())  }
}

val repositoryModule = module {
    single<WeatherRepository>{ WeatherRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    single<WeatherUseCase>{ WeatherUseCaseImpl(get()) }
}

fun createGson(): Gson {
    return GsonBuilder()
        .serializeNulls()
        .setLenient()
        .serializeSpecialFloatingPointValues()
        .enableComplexMapKeySerialization()
        .create()
}

fun createJsonParser(gson: Gson): JsonParser {
    return GsonParser(gson)
}

/**
 * 創建預設的OkHttpClient
 */
private fun createOkHttpClient(
    timeoutMs: Long,
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectionSpecs(
            listOf(
                // allow TLSv1 and TLSv1.1 connections
                ConnectionSpec.COMPATIBLE_TLS,
                // allow http
                ConnectionSpec.CLEARTEXT
            )
        )
        .addLogInterceptor()
        .connectTimeout(timeoutMs, TimeUnit.SECONDS)
        .callTimeout(timeoutMs, TimeUnit.SECONDS)
        .readTimeout(timeoutMs, TimeUnit.SECONDS)
        .writeTimeout(timeoutMs, TimeUnit.SECONDS)
        .build()
}

/**
 * 加入Http Body Log
 */
private fun OkHttpClient.Builder.addLogInterceptor() = apply {
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(interceptor)
    }
}

private fun provideWeatherRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}

private fun provideWeatherService(retrofit: Retrofit): WeatherService = retrofit.create()

private fun createRoomDatabase(context: Context): CacheDatabase {
    return Room.databaseBuilder(context, CacheDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}