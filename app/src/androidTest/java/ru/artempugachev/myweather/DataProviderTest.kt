
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
import ru.artempugachev.myweather.data.*


@RunWith(AndroidJUnit4::class)
class DataProviderTest {
    val context: Context = InstrumentationRegistry.getTargetContext()
    lateinit var dataProvider: DataProvider

    @Before
    fun setUp() {
        deleteDb()
        dataProvider = DataProvider(context)
    }


    @Test
    fun testGetCurData() {
        addRows()
        val curWeather = dataProvider.getCurrentData()

        // todo add timestamp to weather data class
        assertEquals(1496534400, curWeather.timestamp)
    }


    fun addRows(): Int {
        val valArray = getTestWeatherContentValues()
        return context.contentResolver.bulkInsert(WEATHER_URI, valArray)
    }

    fun deleteDb() {
        context.deleteDatabase(DATABASE_NAME)
    }

}