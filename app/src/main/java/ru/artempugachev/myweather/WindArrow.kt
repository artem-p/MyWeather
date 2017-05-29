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

    /**
     * Set wind arrow by wind direction
     * */
    fun setDirection(direction: Int) {
        // stub
        val arrowImageView: ImageView = findViewById(R.id.windArrowImageView) as ImageView
        arrowImageView.setImageResource(R.drawable.wind_south_west)
    }
}
