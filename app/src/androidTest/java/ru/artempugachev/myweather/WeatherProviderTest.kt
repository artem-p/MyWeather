package ru.artempugachev.myweather

import android.content.ContentValues
import android.database.Cursor
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.ProviderTestCase2
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherProviderTest : ProviderTestCase2<WeatherContentProvider>(
        WeatherContentProvider::class.java, AUTHORITY
) {
//    val context: Context = InstrumentationRegistry.getTargetContext()

    @Before
    override public fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        super.setUp()
        deleteAllRows()
    }

    /**
     * Add test rows and then read
     * */
    @Test
    fun testReadRows() {
        addRows()
        val cursor: Cursor = context.contentResolver.query(WEATHER_URI,
                null, null, null, null)

        assertEquals(3, cursor.count)

        cursor.close()
    }

    @Test
    fun testAddRows() {
        val rowInserted = addRows()
        assertEquals(3, rowInserted)
    }

    fun addRows(): Int {
        val cv1: ContentValues = ContentValues()
        cv1.put(WeatherContract.WeatherEntry._ID, 1)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 111)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, 11.3)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_FEELS_LIKE_TEMPERATURE, 14.3)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 75)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1015.5)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_CODE, 35)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION, 270)
        cv1.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5)

        val cv2: ContentValues = ContentValues()
        cv2.put(WeatherContract.WeatherEntry._ID, 2)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 222)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, -11.2)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_FEELS_LIKE_TEMPERATURE, 14.3)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 75)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1015.5)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_CODE, 35)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION, 270)
        cv2.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5)

        val cv3: ContentValues = ContentValues()
        cv3.put(WeatherContract.WeatherEntry._ID, 3)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 333)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_TEMPERATURE, 11.3)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_FEELS_LIKE_TEMPERATURE, 14.3)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 75)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1015.5)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_CODE, 35)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION, 270)
        cv3.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5)

        val valArray: Array<ContentValues> = arrayOf(cv1, cv2, cv3)
        return context.contentResolver.bulkInsert(WEATHER_URI, valArray)
    }

    fun deleteAllRows() {
        context.contentResolver.delete(WEATHER_URI, null, null)
    }

}