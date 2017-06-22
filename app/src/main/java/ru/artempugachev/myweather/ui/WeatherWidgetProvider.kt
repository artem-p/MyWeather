package ru.artempugachev.myweather.ui

import android.appwidget.AppWidgetProvider
import android.widget.RemoteViews
import android.app.PendingIntent
import android.content.Intent
import android.appwidget.AppWidgetManager
import android.content.Context
import ru.artempugachev.myweather.R
import ru.artempugachev.myweather.data.DataProvider


class WeatherWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val widgetCount = appWidgetIds.size
        val dataProvider = DataProvider(context)

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (i in 0..widgetCount - 1) {
            val appWidgetId = appWidgetIds[i]

            val curWeatherData = dataProvider.getCurrentData()
            val views = RemoteViews(context.packageName, R.layout.weather_widget)
            views.setTextViewText(R.id.widgetTempTextView, context.getString(R.string.format_temp,
                    curWeatherData?.minTemp))
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}