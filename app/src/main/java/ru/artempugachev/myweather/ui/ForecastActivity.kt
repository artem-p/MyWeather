package ru.artempugachev.myweather.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ru.artempugachev.myweather.DrawerActivity
import ru.artempugachev.myweather.R

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

        val forecastStub = arrayOf("Forecast 1", "Forecast 2", "Forecast 3")
        adapter.setData(forecastStub)
    }
}
