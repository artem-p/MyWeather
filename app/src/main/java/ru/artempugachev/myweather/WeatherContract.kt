package ru.artempugachev.myweather

import android.provider.BaseColumns

class WeatherContract private constructor(){
    class WeatherEntry : BaseColumns {
        val TABLE_NAME: String = "weather"
        val COLUMN_TIMESTAMP = "timestamp"
        val COLUMN_FEELS_LIKE_TEMPERATURE = "feels_like_temperature"
        val COLUMN_TEMPERATURE = "temperature"
        val COLUMN_HUMIDITY = "humidity"
        val COLUMN_PRESSURE = "pressure"
        val COLUMN_WEATHER_CODE = "weather_code"
        val COLUMN_WIND_DIRECTION = "wind_direction"
        val COLUMN_WIND_SPEED = "wind_speed"
    }
}
