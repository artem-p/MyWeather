package ru.artempugachev.myweather

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
class DbHelperTest {
    val context: Context = InstrumentationRegistry.getTargetContext()
    val dbHelper: WeatherDbHelper
    val db: SQLiteDatabase

    init {
        dbHelper = WeatherDbHelper(context)
        db = dbHelper.writableDatabase
    }

    @Before
    fun setUp() {
        deleteDb()
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

    }

    fun deleteDb() {
        context.deleteDatabase(DATABASE_NAME)
    }

}