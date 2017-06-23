package ru.artempugachev.myweather.ui

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import ru.artempugachev.myweather.MainActivity
import ru.artempugachev.myweather.R
import java.util.*

/**
 * Receiver for show notification. Trigger by alarm
 */

class WeatherNotificatinReceiver : BroadcastReceiver(){
    private val WEATHER_NOTIFICATION_PENDING_INTENT_ID = 42

    override fun onReceive(context: Context, intent: Intent?) {
        showWeatherNotification(context)
    }

    fun setAlarm(context: Context) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, 10)
        val firstTime = calendar.timeInMillis

        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val sendNotificationPendingIntent = PendingIntent.getBroadcast(context, 0,
                Intent(context, this::class.java), 0)
        am.set(AlarmManager.RTC_WAKEUP, firstTime, sendNotificationPendingIntent)
    }

    private fun showWeatherNotification(context: Context) {
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