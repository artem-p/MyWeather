package ru.artempugachev.myweather

import junit.framework.Assert.assertEquals
import org.junit.Test

class WindTest {
    @Test
    fun test_wind_arrow_resource() {
        val wind = Wind(0.0, 0)
        val arrowResource = wind.toWindArrowResource()
        assertEquals(R.drawable.wind_north, arrowResource)
    }
}