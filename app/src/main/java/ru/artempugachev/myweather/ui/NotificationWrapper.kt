package ru.artempugachev.myweather.ui

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import ru.artempugachev.myweather.MainActivity
import ru.artempugachev.myweather.R

/**
 * We use this class for create and show notifications
 */

class NotificationWrapper {
    private val WEATHER_NOTIFICATION_PENDING_INTENT_ID = 42
    fun showWeatherNotification(context: Context) {
        val builder = NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.cloudy)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setStyle(NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.notification_text)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(getContentIntent(context))
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.priority = Notification.PRIORITY_HIGH;
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(WEATHER_NOTIFICATION_PENDING_INTENT_ID, builder.build())
    }

    private fun getContentIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(context,
                WEATHER_NOTIFICATION_PENDING_INTENT_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        return pendingIntent
    }
}