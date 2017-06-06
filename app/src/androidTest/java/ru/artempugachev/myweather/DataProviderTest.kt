package ru.artempugachev.myweather

import android.database.Cursor
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.ProviderTestCase2
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.artempugachev.myweather.data.AUTHORITY
import ru.artempugachev.myweather.data.WEATHER_URI
import ru.artempugachev.myweather.data.WeatherContentProvider
import ru.artempugachev.myweather.data.getTestWeatherContentValues


@RunWith(AndroidJUnit4::class)
class DataProviderTest : ProviderTestCase2<WeatherContentProvider>(
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
        val valArray = getTestWeatherContentValues()
        return context.contentResolver.bulkInsert(WEATHER_URI, valArray)
    }

    fun deleteAllRows() {
        context.contentResolver.delete(WEATHER_URI, null, null)
    }

}