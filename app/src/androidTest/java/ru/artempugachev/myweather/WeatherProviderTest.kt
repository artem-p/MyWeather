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
        val valArray = getTestContentValues()
        return context.contentResolver.bulkInsert(WEATHER_URI, valArray)
    }

    fun deleteAllRows() {
        context.contentResolver.delete(WEATHER_URI, null, null)
    }

}