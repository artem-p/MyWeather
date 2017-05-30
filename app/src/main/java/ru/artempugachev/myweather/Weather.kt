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
        return WeatherData("14.4", R.drawable.cloudy, "Cloudy")
    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val temperature: String, val weatherIcon: Int,
                              val weatherDescription: String) {

}