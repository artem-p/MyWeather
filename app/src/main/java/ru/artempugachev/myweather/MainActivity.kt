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

        // README
        // we cannot use databinding with data variable in xml file
        // due to annotations problem in kapt
        // hope it will be fixed
        binding.tvTemperature.setText(getString(R.string.format_temp_celsium,
                                                            weatherData.temperature))
        binding.tvWeather.setText(weatherData.weatherdescription)
        binding.weatherIcon.setImageResource(weatherData.weatherIcon)

//        binding.tvTemperature.setText(weatherData.temperature.toString())
        // todo finish binding with example object
    }
}

