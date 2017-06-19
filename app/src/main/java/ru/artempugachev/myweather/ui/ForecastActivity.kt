package ru.artempugachev.myweather.ui

import android.database.Cursor
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ru.artempugachev.myweather.DrawerActivity
import ru.artempugachev.myweather.R
import ru.artempugachev.myweather.data.WEATHER_URI
import ru.artempugachev.myweather.weather.WeatherData
import ru.artempugachev.myweather.weather.Wind

class ForecastActivity : DrawerActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        createDrawer()

        recyclerView = findViewById(R.id.rv_forecast) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = ForecastAdapter()
        recyclerView.adapter = adapter

        LoadForecastTask().execute()
    }

    inner class LoadForecastTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            val cursor: Cursor = contentResolver.query(WEATHER_URI,
                    null, null, null, null)

            adapter.swapCursor(cursor)
        }
    }
}
