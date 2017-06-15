package ru.artempugachev.myweather.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.artempugachev.myweather.R

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    lateinit var forecastData: Array<String>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmideately = false

        val view = inflater.inflate(R.layout.forecast_list_item, parent, shouldAttachToParentImmideately)
        return ForecastViewHolder(view)
    }


    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bindForecast(forecastData[position])
    }


    override fun getItemCount(): Int {
        return forecastData.size
    }

    fun setData(data: Array<String>) {
        forecastData = data
        notifyDataSetChanged()
    }

    class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val forecastTextView: TextView = view.findViewById(R.id.forecastText) as TextView

        fun bindForecast(forecast: String) {
            forecastTextView.setText(forecast)
        }
    }
}