package ru.artempugachev.myweather

import android.net.Uri
import android.provider.BaseColumns

val AUTHORITY = "ru.artempugachev.myweather"
val BASE_CONTENT_URI = Uri.parse("content://$AUTHORITY")
val PATH_WEATHER = "weather"
val CONTENT_URI = BASE_CONTENT_URI.buildUpon()
        .appendPath(PATH_WEATHER)
        .build()

class WeatherContract private constructor(){
    class WeatherEntry : BaseColumns {
        companion object {
            val TABLE_NAME: String = "weather"
            val _ID: String = "_id"   // it is in BaseColumns though, but I don't know how to
                                            // access it from this kotlin class
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
}
