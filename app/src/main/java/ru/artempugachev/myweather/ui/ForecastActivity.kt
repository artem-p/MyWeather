package ru.artempugachev.myweather.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import ru.artempugachev.myweather.DrawerActivity
import ru.artempugachev.myweather.R

class ForecastActivity : DrawerActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        createDrawer()
    }
}
