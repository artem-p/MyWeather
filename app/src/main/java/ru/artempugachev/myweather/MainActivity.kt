package ru.artempugachev.myweather

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.artempugachev.myweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val weather: Weather = Weather()
        val weatherData: WeatherData = weather.toWeatherData()

        binding.tvTemperature.setText(weatherData.temperature.toString())
        // todo finish binding with example object
    }
}

