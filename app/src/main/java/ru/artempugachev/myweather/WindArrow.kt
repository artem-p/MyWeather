package ru.artempugachev.myweather

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView

/**
 * Component for wind arrow
 */

class WindArrow(context: Context) : FrameLayout(context) {
    constructor(context: Context, attrs: AttributeSet?) : this(context) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.wind_arrow, this)

        val attrArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.WindArrow)
        val windDir: Int = attrArray.getInt(R.styleable.WindArrow_wind_dir, 0)
        attrArray.recycle()

        setDirection(windDir)
    }


    fun setDirection(direction: Int) {
        val arrowImageView = findViewById(R.id.windArrowImageView) as ImageView
        val wind = Wind(0.0, direction)
        val arrowResource = wind.toWindArrowResource()
        arrowImageView.setImageResource(arrowResource)
    }
}
