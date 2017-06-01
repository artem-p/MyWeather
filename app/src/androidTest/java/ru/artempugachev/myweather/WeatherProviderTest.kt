package ru.artempugachev.myweather

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
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

    @Test
    fun testAddReadRows() {
        // todo stub
    }

    fun deleteAllRows() {
        context.contentResolver.delete(CONTENT_URI, null, null)
    }

}