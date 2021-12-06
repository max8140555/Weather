package com.max.android_perona.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.max.android_perona.R
import com.max.android_perona.databinding.ActivityMainBinding
import com.max.android_perona.model.cache.WeatherSharedPreferences
import com.max.android_perona.view.weather.WeatherFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedPreferences: WeatherSharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (sharedPreferences.isFirstOpenApp) {
            sharedPreferences.isFirstOpenApp = false
        } else {
            Toast.makeText(this, getString(R.string.welcome_back), Toast.LENGTH_LONG).show()
        }

        val initFragment = WeatherFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragmentContainerView, initFragment)
        transaction.commit()
    }
}