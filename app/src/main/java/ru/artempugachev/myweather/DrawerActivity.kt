package ru.artempugachev.myweather

import android.content.Intent
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * We need drawer_list on all activities so move it to base class
 */

open class DrawerActivity : AppCompatActivity() {
    lateinit var drawerList: ListView
    lateinit var drawerAdapter: ArrayAdapter<String>
    lateinit var drawerLayout: DrawerLayout

    protected fun createDrawer() {
        drawerList = findViewById(R.id.drawer_list) as ListView
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        val drawerArray = arrayOf(getString(R.string.now_label), getString(R.string.forecast_label))
        drawerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerArray)
        drawerList.adapter = drawerAdapter

        drawerList.setOnItemClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val intent: Intent?
            when (position) {
                0 -> intent = Intent(this, MainActivity::class.java)
                1 -> intent = Intent(this, ForecastActivity::class.java)
                else -> intent = null
            }
            if (intent != null) startActivity(intent)
            drawerLayout.closeDrawers()
        }
    }

}
