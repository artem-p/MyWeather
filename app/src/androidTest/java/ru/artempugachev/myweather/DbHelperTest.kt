package ru.artempugachev.myweather

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.artempugachev.myweather.data.DATABASE_NAME
import ru.artempugachev.myweather.data.WeatherContract
import ru.artempugachev.myweather.data.WeatherDbHelper


@RunWith(AndroidJUnit4::class)
class DbHelperTest {
    val context: Context = InstrumentationRegistry.getTargetContext()
    lateinit var dbHelper: WeatherDbHelper
    lateinit var db: SQLiteDatabase

    @Before
    fun setUp() {
        deleteDb()
        dbHelper = WeatherDbHelper(context)
        db = dbHelper.writableDatabase
    }

    /**
     * Attempt to get db instance and check if it is open
     * */
    @Test
    fun testCreateDb() {
        assertEquals("Database should be open and it isn't", true, db.isOpen)
    }


    /**
     * Add test vals to db and read it
     * */
    @Test
    fun testDbWriteRead() {
        val cv: ContentValues = ContentValues()
        cv.put(WeatherContract.WeatherEntry._ID, 1)
        cv.put(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP, 111)
        cv.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMPERATURE, 14.3)
        cv.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMPERATURE, 11.3)
        cv.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 75)
        cv.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1015.5)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ICON, "cloud")
        cv.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_DESCRIPTION, "cloud")
        cv.put(WeatherContract.WeatherEntry.COLUMN_WIND_DIRECTION, 270)
        cv.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5)

        val insertedNumber = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, cv)
        assertEquals(1, insertedNumber)

        val projection: Array<String> = arrayOf(WeatherContract.WeatherEntry._ID,
                WeatherContract.WeatherEntry.COLUMN_TIMESTAMP)

        val cursor = db.query(WeatherContract.WeatherEntry.TABLE_NAME,
                projection, null, null, null, null, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry._ID))
        val timestamp = cursor.getInt(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_TIMESTAMP))

        assertEquals(1, id)
        assertEquals(111, timestamp)

        cursor.close()
    }

    fun deleteDb() {
        context.deleteDatabase(DATABASE_NAME)
    }

}