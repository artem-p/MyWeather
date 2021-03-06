package ru.artempugachev.myweather

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.LoaderManager.LoaderCallbacks
import android.support.v4.content.AsyncTaskLoader
import android.support.v4.content.Loader
import ru.artempugachev.myweather.data.DataProvider
import ru.artempugachev.myweather.data.WeatherSyncJobInitializer
import ru.artempugachev.myweather.data.startWeatherSyncNow
import ru.artempugachev.myweather.databinding.ActivityMainBinding
import ru.artempugachev.myweather.ui.WeatherNotificatinReceiver
import ru.artempugachev.myweather.weather.WeatherData


val WEATHER_LOADER_ID = 42

class MainActivity : DrawerActivity(), LoaderCallbacks<WeatherData> {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        createDrawer()

        val weatherSyncJobInitializer = WeatherSyncJobInitializer()
        weatherSyncJobInitializer.scheduleWeatherSyncJobService(this)
        startWeatherSyncNow(this)

        supportLoaderManager.initLoader(WEATHER_LOADER_ID, null, this)

        val notificationReceiver = WeatherNotificatinReceiver()
        notificationReceiver.setAlarm(this)
    }


    /**
     * Loader methods
     * */
    override fun onCreateLoader(loaderId: Int, args: Bundle?): Loader<WeatherData> {
        when (loaderId) {
            WEATHER_LOADER_ID -> {
                return WeatherLoader(this)
            }

            else -> {
                throw RuntimeException("Loader not implemented")
            }
        }
    }

    override fun onLoadFinished(loader: Loader<WeatherData>?, weatherData: WeatherData?) {
        bindCurWeatherData(weatherData)
    }

    override fun onLoaderReset(loader: Loader<WeatherData>?) {
        bindCurWeatherData(null)
    }

    fun bindCurWeatherData(weatherData: WeatherData?) {
        if (weatherData != null) {
            binding.weatherData = weatherData
        } else {
            // todo implement output with no data
        }
    }
}

class WeatherLoader(context: Context?) : AsyncTaskLoader<WeatherData>(context) {
    override fun onStartLoading() {
        forceLoad()
    }

    override fun loadInBackground(): WeatherData? {
        // query current data from database
        val dataProvider = DataProvider(context)
        return dataProvider.getCurrentData()
    }

    override fun deliverResult(weatherData: WeatherData?) {
        super.deliverResult(weatherData)
    }
}

