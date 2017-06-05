package ru.artempugachev.myweather

import android.content.Context
import android.database.Cursor

/**
 * Wrapper on WeatherProvider with helper methods
 */

class DataProvider {
    /**
     * Query data with provider and prepare WeatherData data class
     * */
    fun getCurrentData(context: Context): WeatherData? {
        val sortOrder = "${WeatherContract.WeatherEntry.COLUMN_TIMESTAMP} DESC"

        val cursor: Cursor = context.contentResolver.query(WEATHER_URI,
                null, null, null, sortOrder)

        val weatherData: WeatherData?

        weatherData = if (cursor.moveToFirst()) {
            val timestamp = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP))
            val temperature = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE))
            val weatherCode = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_CODE))
            val windSpeed = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED))
            val windDir = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION))

            val weather: Weather = Weather(timestamp, temperature, weatherCode, Wind(windSpeed, windDir))
            weather.toWeatherData()
        } else {
            null
        }

        cursor.close()

        return weatherData
    }
}
