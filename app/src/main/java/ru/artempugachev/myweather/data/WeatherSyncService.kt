package ru.artempugachev.myweather.data

import android.app.IntentService
import android.content.Intent

/**
 * Service for weather sync
 * It is subclass of {@link IntentService} so it will be on separate thread
 * Get weather from weather provider and put in to database via content provider
 */

class WeatherSyncService : IntentService("WeatherSyncService") {
    override fun onHandleIntent(intent: Intent?) {
        syncWeather()
    }

    @Synchronized
    private fun syncWeather() {

    }
}
