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

        if (cursor.moveToFirst()) {
//            val temperature
        } else {
            weatherData = null
        }
        cursor.close()
    }


}
