package ru.artempugachev.myweather

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

val CODE_WEATHER = 100
val CODE_WEATHER_WITH_TIME = 101

class WeatherContentProvider : ContentProvider() {
    lateinit var dbHelper: WeatherDbHelper
    val uriMatcher = buildUriMatcher()

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        val authority = AUTHORITY
        matcher.addURI(authority, PATH_WEATHER, CODE_WEATHER)
        matcher.addURI(authority, "$PATH_WEATHER/#", CODE_WEATHER_WITH_TIME)
        return matcher
    }

    override fun onCreate(): Boolean {
        dbHelper = WeatherDbHelper(context)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bulkInsert(uri: Uri, values: Array<ContentValues>): Int {
        val db = dbHelper.writableDatabase

        when (uriMatcher.match(uri)) {
            CODE_WEATHER -> {
                db.beginTransaction()
                var rowsInserted = 0
                try {
                    for (value in values) {
                        val id = db.insert(WeatherContract.WeatherEntry.TABLE_NAME,
                                null, value)
                        if (id != -1L) {
                            rowsInserted++
                        }
                    }
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
                return rowsInserted
            }

            else -> {
                return super.bulkInsert(uri, values)
            }
        }
    }


    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}