package ru.artempugachev.myweather

import android.net.Uri
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Class for weather provider. Weather provider responds for
 * fetching weather data
 */

class DarkSkyProvider(private val mApiKey: String) {
    private val BASE_URL = "https://api.darksky.net/forecast"
    // parameters here https://darksky.net/dev/docs/forecast
    private val LANG_PARAM_KEY = "lang"
    private val LANG_PARAM_VALUE = "ru"
    private val UNITS_PARAM_KEY = "units"
    private val UNITS_PARAM_VALUE = "si"
    private val CURRENTLY_JSON_PARAM = "currently"
    private val HOURLY_JSON_PARAM = "hourly"
    private val TIMESTAMP_JSON_PARAM = "time"
    private val SUMMARY_JSON_PARAM = "summary"
    private val ICON_JSON_PARAM = "icon"
    private val TEMPERATURE_JSON_PARAM = "temperature"
    private val APPARENT_TEMPERATURE_JSON_PARAM = "apparentTemperature"
    private val HUMIDITY_JSON_PARAM = "humidity"
    private val WIND_SPEED_JSON_PARAM = "windSpeed"
    private val WIND_DIR_JSON_PARAM = "windBearing"


    @Throws(IOException::class, JSONException::class)
    fun fetchCurrent(coordinate: Coordinate): Weather {
        val url = buildCurrentUrl(coordinate)
        val respStr = fetchByUrl(url)
        val weatherJson = JSONObject(respStr)
        val currentlyJson = weatherJson.getJSONObject(CURRENTLY_JSON_PARAM)
        val forecastJson = weatherJson.getJSONObject(HOURLY_JSON_PARAM)
        return buildWeatherFromJson(currentlyJson, forecastJson)
    }

    @Throws(IOException::class)
    private fun fetchByUrl(url: URL): String {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    @Throws(JSONException::class)
    fun buildWeatherFromJson(jsonCurrent: JSONObject, jsonForecast: JSONObject): Weather {
        val timestampStr = jsonCurrent.getString(TIMESTAMP_JSON_PARAM)
        val summary = jsonCurrent.getString(SUMMARY_JSON_PARAM)
        val icon = jsonCurrent.getString(ICON_JSON_PARAM)
        val temperatureStr = jsonCurrent.getString(TEMPERATURE_JSON_PARAM)
        val apparentTemperatureStr = jsonCurrent.getString(APPARENT_TEMPERATURE_JSON_PARAM)
        val humidityStr = jsonCurrent.getString(HUMIDITY_JSON_PARAM)
        val windSpeedStr = jsonCurrent.getString(WIND_SPEED_JSON_PARAM)
        val windDirStr = jsonCurrent.getString(WIND_DIR_JSON_PARAM)
        val forecastSummary = jsonForecast.getString(SUMMARY_JSON_PARAM)
        return Weather(timestampStr.toInt(), temperatureStr.toDouble(),
                icon, Wind(windSpeedStr.toDouble(), windDirStr.toInt()))
    }


    @Throws(MalformedURLException::class)
    fun buildCurrentUrl(coordinate: Coordinate): URL {
        val builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(mApiKey)
                .appendPath(coordinate.toUrlPath())
                .appendQueryParameter(LANG_PARAM_KEY, LANG_PARAM_VALUE)
                .appendQueryParameter(UNITS_PARAM_KEY, UNITS_PARAM_VALUE).build()

        return URL(builtUri.toString())
    }

    companion object {
        val UPDATE_INTERVAL_MS = 60 * 1000 * 15 // 15 min
    }
}
