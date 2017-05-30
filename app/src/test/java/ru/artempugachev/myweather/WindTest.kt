package ru.artempugachev.myweather

import junit.framework.Assert.assertEquals
import org.junit.Test

class WindTest {
    @Test
    fun test_wind_arrow_resource() {
        assertEquals(R.drawable.wind_north, Wind(0.0, 0).toWindArrowResource())
        assertEquals(R.drawable.wind_north, Wind(0.0, 23).toWindArrowResource())
        assertEquals(R.drawable.wind_north_east, Wind(0.0, 24).toWindArrowResource())
        assertEquals(R.drawable.wind_north_east, Wind(0.0, 67).toWindArrowResource())
        assertEquals(R.drawable.wind_east, Wind(0.0, 68).toWindArrowResource())
        assertEquals(R.drawable.wind_east, Wind(0.0, 112).toWindArrowResource())
        assertEquals(R.drawable.wind_south_east, Wind(0.0, 113).toWindArrowResource())
        assertEquals(R.drawable.wind_south_east, Wind(0.0, 157).toWindArrowResource())
        assertEquals(R.drawable.wind_south, Wind(0.0, 158).toWindArrowResource())
        assertEquals(R.drawable.wind_south, Wind(0.0, 202).toWindArrowResource())
        assertEquals(R.drawable.wind_south_west, Wind(0.0, 203).toWindArrowResource())
        assertEquals(R.drawable.wind_south_west, Wind(0.0, 247).toWindArrowResource())
        assertEquals(R.drawable.wind_west, Wind(0.0, 248).toWindArrowResource())
        assertEquals(R.drawable.wind_west, Wind(0.0, 292).toWindArrowResource())
        assertEquals(R.drawable.wind_north_west, Wind(0.0, 293).toWindArrowResource())
        assertEquals(R.drawable.wind_north_west, Wind(0.0, 337).toWindArrowResource())
        assertEquals(R.drawable.wind_north, Wind(0.0, 338).toWindArrowResource())
        assertEquals(R.drawable.wind_north, Wind(0.0, 359).toWindArrowResource())
    }
}