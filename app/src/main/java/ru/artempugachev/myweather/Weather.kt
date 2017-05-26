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
        return WeatherData(14.5, R.drawable.cloudy, "Cloudy")
    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val temperature: Double, val weatherIcon: Int,
                              val weatherdescription: String) {

}