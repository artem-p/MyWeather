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

        val cursor: Cursor = context.contentResolver.query(WEATHER_URI,
                null, null, null, null)

        val weatherData: WeatherData?

        weatherData = if (cursor.moveToFirst()) {
            val timestamp = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP))
            val minTemp = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMPERATURE))
            val maxTemp = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMPERATURE))
            val weatherIcon = cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_ICON))
            val weatherDescription = cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_DESCRIPTION))
            val windSpeed = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED))
            val windDir = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION))

            val weather: Weather = Weather(timestamp, minTemp, maxTemp, weatherIcon, weatherDescription, Wind(windSpeed, windDir))
            weather.toWeatherData()
        } else {
            null
        }

        cursor.close()

        return weatherData
    }

    /**
     * Write array of weatherArray data to db
     * */
    fun writeWeather(weatherList: List<Weather>) {
        if (weatherList.isNotEmpty()) {
            val weatherCv = weatherList.map{weather -> weather.toContentValues() }
            val weatherCvArray = weatherCv.toTypedArray()
            val rowsInserted = context.contentResolver.bulkInsert(WEATHER_URI, weatherCvArray)
        }
    }

    /**
     * Delete all the data
     * */
    fun deleteData() {
        context.contentResolver.delete(WEATHER_URI, null, null)
    }
}
