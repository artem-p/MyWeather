package ru.artempugachev.myweather.weather

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityEvent
import android.widget.FrameLayout
import android.widget.ImageView
import ru.artempugachev.myweather.R
import ru.artempugachev.myweather.weather.Wind

/**
 * Component for wind arrow
 */

class WindArrow(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    lateinit var wind: Wind
    fun setDirection(direction: Int) {
        val arrowImageView = findViewById(R.id.windArrowImageView) as ImageView
        wind = Wind(0.0, direction)
        val arrowResource = wind.toWindArrowResource()
        arrowImageView.setImageResource(arrowResource)
    }

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.wind_arrow, this)
        val attrArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.WindArrow)
        val windDir: Int = attrArray.getInt(R.styleable.WindArrow_direction, 0)
        attrArray.recycle()
        setDirection(windDir)
    }

    override fun onPopulateAccessibilityEvent(event: AccessibilityEvent?) {
        super.onPopulateAccessibilityEvent(event)

        event?.text?.add(wind.direction.toString())
    }
}
