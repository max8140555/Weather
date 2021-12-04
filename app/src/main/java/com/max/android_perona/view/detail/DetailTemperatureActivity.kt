package com.max.android_perona.view.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.max.android_perona.databinding.ActivityDetailTemperatureBinding

class DetailTemperatureActivity : AppCompatActivity() {

    companion object {
        private const val ARG_DATA = "argData"

        fun createIntent(context: Context, data: DetailTemperature): Intent {
            return Intent(context, DetailTemperatureActivity::class.java).apply {
                putExtra(ARG_DATA, data)
            }
        }
    }

    private lateinit var binding: ActivityDetailTemperatureBinding

    private val initDetailTemperature by lazy {
        intent.getParcelableExtra<DetailTemperature>(ARG_DATA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextView(initDetailTemperature)
    }

    @SuppressLint("SetTextI18n")
    fun setTextView(data: DetailTemperature?) {
        data ?: return

        binding.textViewStartTime.text = data.startTime
        binding.textViewEndTime.text = data.endTime
        binding.textViewTemperature.text = "${data.value}${data.unit}"
    }
}