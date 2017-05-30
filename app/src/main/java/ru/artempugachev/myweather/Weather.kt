package ru.artempugachev.myweather

/**
 * Representation of weather
 * */
class Weather() {

    /**
     * Transform to data class for binding
     */
    fun toWeatherData(): WeatherData {
        // stub
        return WeatherData(14.4, R.drawable.cloudy, "Cloudy", Wind(0.0, 270))
    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val temperature: Double, val weatherIcon: Int,
                              val weatherDescription: String, val wind: Wind) {

}