package com.max.android_perona.model.cache

import android.content.Context
import androidx.core.content.edit

class WeatherSharedPreferences(
    context: Context
) {

    companion object {
        private const val SHARED_PREFERENCES_FILE_NAME = "SharedPreferenceFileName"
        private const val IS_FIRST_OPEN_APP = "isFirstOpenApp"
    }
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_FILE_NAME,
        Context.MODE_PRIVATE
    )

    var isFirstOpenApp: Boolean
        get() = sharedPreferences.getBoolean(IS_FIRST_OPEN_APP, true)
        set(value) = sharedPreferences.edit { putBoolean(IS_FIRST_OPEN_APP, value) }
}