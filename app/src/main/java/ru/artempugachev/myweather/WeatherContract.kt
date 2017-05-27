package ru.artempugachev.myweather

import android.provider.BaseColumns

class WeatherContract private constructor(){
    class WeatherEntry : BaseColumns {
        val TABLE_NAME: String = "weather"
        val COLUMN_TIMESTAMP = "timestamp"
        val COLUMN_TEMPERATURE = "temperature"
        val COLUMN_WEATHER_CODE = "weather_code"
    }
}
