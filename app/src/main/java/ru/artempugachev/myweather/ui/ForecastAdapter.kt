package ru.artempugachev.myweather.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.artempugachev.myweather.R
import ru.artempugachev.myweather.weather.WeatherData

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    lateinit var forecastData: Array<WeatherData>
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(R.layout.forecast_list_item, parent, shouldAttachToParentImmediately)
        return ForecastViewHolder(view)
    }


    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bindForecast(forecastData[position], context)
    }


    override fun getItemCount(): Int {
        return forecastData.size
    }

    fun setData(forecasts: Array<WeatherData>) {
        forecastData = forecasts
        notifyDataSetChanged()
    }

    class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val minTempTextView: TextView = view.findViewById(R.id.min_temperature) as TextView
        val maxTempTextView: TextView = view.findViewById(R.id.max_temperature) as TextView
        val descriptionTextView: TextView = view.findViewById(R.id.description) as TextView

        fun bindForecast(forecast: WeatherData, context: Context) {
            minTempTextView.text = context.getString(R.string.format_temp, forecast.temperature)
            maxTempTextView.text = context.getString(R.string.format_temp, forecast.temperature)
            descriptionTextView.text = forecast.weatherDescription
        }
    }
}