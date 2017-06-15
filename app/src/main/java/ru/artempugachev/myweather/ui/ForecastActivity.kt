package ru.artempugachev.myweather.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ru.artempugachev.myweather.DrawerActivity
import ru.artempugachev.myweather.R
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

        val forecastStub = arrayOf(
                WeatherData(1496528520, 15.5, R.drawable.cloudy, "Cloud", Wind(5.5, 0)),
                WeatherData(1496528521, 15.6, R.drawable.cloudy, "Cloud", Wind(3.5, 90)),
                WeatherData(1496528522, 15.7, R.drawable.cloudy, "Cloud", Wind(1.5, 270)))
        adapter.setData(forecastStub)
    }
}
