package ru.artempugachev.myweather

import android.content.ContentValues
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherProviderTest {
    val context: Context = InstrumentationRegistry.getTargetContext()

    @Before
    fun setUp() {
        deleteAllRows()
    }

    /**
     * Add test rows and then read
     * */
    @Test
    fun testAddReadRows() {
        val cv1: ContentValues = ContentValues()
        cv1.put(WeatherContract.WeatherEntry._ID, 1)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 111)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, 11)

        val cv2: ContentValues = ContentValues()
        cv2.put(WeatherContract.WeatherEntry._ID, 2)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 222)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, -11.2)

        val cv3: ContentValues = ContentValues()
        cv3.put(WeatherContract.WeatherEntry._ID, 3)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 333)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, 11.3)

        val valArray: Array<ContentValues> = arrayOf(cv1, cv2, cv3)
        context.contentResolver.bulkInsert(WEATHER_URI, valArray)
    }

    fun deleteAllRows() {
        context.contentResolver.delete(WEATHER_URI, null, null)
    }

}