package ru.artempugachev.myweather

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DbHelperTest {
    val context: Context = InstrumentationRegistry.getTargetContext()

    @Before
    fun setUp() {
        deleteDb()
    }

    @Test
    fun testCreateDb() {
        val dbHelper = WeatherDbHelper(context)
        // todo stub
        assertEquals(4, 2 + 2)
    }

    fun deleteDb() {
        context.deleteDatabase(DATABASE_NAME)
    }

}