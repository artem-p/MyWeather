package ru.artempugachev.myweather.weather

import ru.artempugachev.myweather.R

/**
 * Representation of weather
 * */
class Weather(val timestamp: Int, val temperature: Double, val icon: String,
              val wind: Wind) {

    /**
     * Transform to data class for binding
     */
    fun toWeatherData(): WeatherData {
        // todo stub
        return WeatherData(temperature, R.drawable.cloudy, "Cloudy", wind)
    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val temperature: Double, val weatherIcon: Int,
                              val weatherDescription: String, val wind: Wind) {

}