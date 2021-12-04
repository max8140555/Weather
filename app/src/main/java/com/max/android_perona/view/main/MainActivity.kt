package com.max.android_perona.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.max.android_perona.R
import com.max.android_perona.databinding.ActivityMainBinding
import com.max.android_perona.view.weather.WeatherFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val initFragment = WeatherFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragmentContainerView, initFragment)
        transaction.commit()
    }
}