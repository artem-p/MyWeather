package ru.artempugachev.myweather

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.LoaderManager.LoaderCallbacks
import android.support.v4.content.AsyncTaskLoader
import android.support.v4.content.Loader
import ru.artempugachev.myweather.data.DataProvider
import ru.artempugachev.myweather.databinding.ActivityMainBinding
import ru.artempugachev.myweather.weather.*

val WEATHER_LOADER_ID = 42

class MainActivity : DrawerActivity(), LoaderCallbacks<WeatherData> {
    lateinit var binding: ActivityMainBinding
    lateinit var curWeatherData: WeatherData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val weather: Weather = Weather(1, 14.4, 7.5, "cloud", "Cloud", Wind(0.0, 0))
        curWeatherData = weather.toWeatherData()

        binding.weatherData = curWeatherData

        createDrawer()
        loadWeatherData()

        // todo this is for debug, delete later
        Thread.sleep(1000)
        /////////////////////////////////////////

        supportLoaderManager.initLoader(WEATHER_LOADER_ID, null, this)
    }


    private fun loadWeatherData() {
        FetchWeatherDataTask().execute()
    }


    /**
     * This async task is for test data fetching from darksky
     * Later we'll transform it to job service
     * */
    inner class FetchWeatherDataTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            val darkSkyProvider = DarkSkyProvider(BuildConfig.DARK_SKY_API_KEY)
            val curWeather = darkSkyProvider.fetchCurrent(Coordinate("59.93", "30.29"))

            val dataProvider = DataProvider(this@MainActivity)
            dataProvider.writeWeather(arrayOf(curWeather))
        }

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

