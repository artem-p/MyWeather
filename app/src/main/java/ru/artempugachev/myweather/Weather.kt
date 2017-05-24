package ru.artempugachev.myweather

/**
 * Representation of weather
 * */
class Weather() {

    /**
     * Transform to data class for binding
     */
    fun toWeatherData() {

    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val temperature: Float, val weatherIconName: String,
                              val weatherdescription: String) {

}