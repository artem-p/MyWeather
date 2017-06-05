package ru.artempugachev.myweather

import android.database.Cursor
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.app.LoaderManager.LoaderCallbacks
import android.support.v4.content.AsyncTaskLoader
import android.support.v4.content.Loader
import ru.artempugachev.myweather.databinding.ActivityMainBinding

val WEATHER_LOADER_ID = 42

class MainActivity : AppCompatActivity(), LoaderCallbacks<WeatherData> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val weather: Weather = Weather()
        val weatherData: WeatherData = weather.toWeatherData()

        binding.weatherData = weatherData

        loadTestData()
    }

    private fun loadTestData() {
        LoadInitDataTask().execute()
    }

    /**
     * We need some data to test loader
     * */
    inner class LoadInitDataTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            contentResolver.delete(WEATHER_URI, null, null)
            val weatherDataStub = getTestWeatherContentValues()
            contentResolver.bulkInsert(WEATHER_URI, weatherDataStub)
        }
    }

    /**
     * Loader methods
     * */
    override fun onCreateLoader(loaderId: Int, args: Bundle?): Loader<WeatherData> {
        when (loaderId) {
            WEATHER_LOADER_ID -> {
            }

            else -> {
                throw RuntimeException("Loader not implemented")
            }
        }
    }

    override fun onLoadFinished(loader: Loader<WeatherData>?, data: WeatherData?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoaderReset(loader: Loader<WeatherData>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class WeatherLoader : AsyncTaskLoader<WeatherData>(this) {
        override fun loadInBackground(): WeatherData {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun deliverResult(data: WeatherData?) {
            super.deliverResult(data)
        }
    }
}

