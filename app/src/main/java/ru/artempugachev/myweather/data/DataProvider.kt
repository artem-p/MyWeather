package ru.artempugachev.myweather.data

import android.content.Context
import android.database.Cursor
import ru.artempugachev.myweather.weather.Weather
import ru.artempugachev.myweather.weather.WeatherData
import ru.artempugachev.myweather.weather.Wind

/**
 * Wrapper on WeatherProvider with helper methods
 */

class DataProvider(val context: Context) {
    /**
     * Query data with provider and prepare WeatherData data class
     * */
    fun getCurrentData(): WeatherData? {
        val sortOrder = "${WeatherContract.WeatherEntry.COLUMN_TIMESTAMP} DESC"

        val cursor: Cursor = context.contentResolver.query(WEATHER_URI,
                null, null, null, sortOrder)

        val weatherData: WeatherData?

        weatherData = if (cursor.moveToFirst()) {
            val timestamp = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP))
            val temperature = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE))
            val weatherIcon = cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_ICON))
            val windSpeed = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED))
            val windDir = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION))

            val weather: Weather = Weather(timestamp, temperature, weatherIcon, Wind(windSpeed, windDir))
            weather.toWeatherData()
        } else {
            null
        }

        cursor.close()

        return weatherData
    }
}
