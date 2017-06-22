package ru.artempugachev.myweather.ui

import android.appwidget.AppWidgetProvider
import android.R.attr.button
import android.widget.RemoteViews
import android.app.PendingIntent
import android.content.Intent
import android.appwidget.AppWidgetManager
import android.content.Context
import ru.artempugachev.myweather.R


class WeatherWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val widgetCount = appWidgetIds.size

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (i in 0..widgetCount - 1) {
            val appWidgetId = appWidgetIds[i]

//            // Get the layout for the App Widget and attach an on-click listener
//            // to the button
//            val views = RemoteViews(context.getPackageName(), R.layout.weather_widget)
//
//            // Tell the AppWidgetManager to perform an update on the current app widget
//            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}