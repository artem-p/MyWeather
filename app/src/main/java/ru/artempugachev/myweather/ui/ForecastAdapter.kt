package ru.artempugachev.myweather.ui

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.artempugachev.myweather.R
import ru.artempugachev.myweather.data.WeatherContract

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    lateinit var context: Context
    lateinit var cursor: Cursor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(R.layout.forecast_list_item, parent, shouldAttachToParentImmediately)
        return ForecastViewHolder(view)
    }


    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bindForecast(cursor, position, context)
    }


    override fun getItemCount(): Int {
        // first position in cursor is for current data, so subtract 1 to get forecast elements count
        // todo cursor with forecast data only
        return cursor.count - 1
    }


    /*
    * Swap cursor and notify data changed
    * This method called by activity when data loading finished
    * */
    fun swapCursor(newCursor: Cursor) {
        cursor = newCursor
        notifyDataSetChanged()
    }

    class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timeTextView: TextView = view.findViewById(R.id.forecast_date) as TextView
        val minTempTextView: TextView = view.findViewById(R.id.min_temperature) as TextView
        val maxTempTextView: TextView = view.findViewById(R.id.max_temperature) as TextView
        val descriptionTextView: TextView = view.findViewById(R.id.description) as TextView

        /**
         * Get data from cursor and bind forecast val to it
         * */
        fun bindForecast(cursor: Cursor, position: Int, context: Context) {
            // First position in cursor is current data. We don't need it here, in forecast
            // So get data from cursor with index as position + 1
            // todo cursor with forecast data only

            val cursorPosition: Int = position + 1

            if (position < cursor.count) {
                cursor.moveToPosition(position)
                timeTextView.text = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP)).toString()
                minTempTextView.text = context.getString(R.string.format_temp,
                        cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMPERATURE)))
                maxTempTextView.text = context.getString(R.string.format_temp,
                        cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMPERATURE)))
                descriptionTextView.text = cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_DESCRIPTION))
            }
        }
    }
}