package com.max.android_perona

import android.app.Application
import com.max.android_perona.di.appCoreModule
import com.max.android_perona.di.repositoryModule
import com.max.android_perona.di.useCaseModule
import com.max.android_perona.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApplication : Application() {

    companion object {
        /**
         * Application實體
         */
        @JvmStatic
        lateinit var instance: WeatherApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@WeatherApplication)
            if (BuildConfig.DEBUG) {
                androidLogger(Level.NONE)
            }
            modules(
                listOf(
                    viewModelModule,
                    appCoreModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }

}
