package ru.artempugachev.myweather

import android.os.Bundle
import android.support.v7.widget.RecyclerView

class ForecastActivity : DrawerActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        createDrawer()
    }
}
