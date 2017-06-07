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
        return WeatherData(timestamp, temperature, R.drawable.cloudy, "Cloudy", wind)
    }

    /**
     * Transform to content values for db writing
     * */
    fun toContentValues(): ContentValues {
        val cv: ContentValues = ContentValues()
        cv.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, timestamp)
        cv.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, temperature)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ICON, icon)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION, wind.direction)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, wind.speed)

        // todo not implemented vals yet
        cv.put(WeatherContract.WeatherEntry.COLUMN_FEELS_LIKE_TEMPERATURE, 0.0)
        cv.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 0)
        cv.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 0)


        return cv
    }
}


/**
 * Data class only for databinding
 * */
data class WeatherData(val timestamp: Int, val temperature: Double, val weatherIcon: Int,
                              val weatherDescription: String, val wind: Wind) {

}