package ru.artempugachev.myweather.weather

import android.content.ContentValues
import ru.artempugachev.myweather.R
import ru.artempugachev.myweather.data.WeatherContract

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

    /**
     * Transform to content values for db writing
     * */
    fun toContentValues(): ContentValues {
        val cv: ContentValues = ContentValues()
        cv.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 1496534400)
        cv.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, 11.3)
        cv.put(WeatherContract.WeatherEntry.COLUMN_FEELS_LIKE_TEMPERATURE, 14.3)
        cv.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 75)
        cv.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1015.5)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ICON, "cloud")
        cv.put(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION, 270)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5)
        return cv
    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val temperature: Double, val weatherIcon: Int,
                              val weatherDescription: String, val wind: Wind) {

}