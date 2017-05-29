package ru.artempugachev.myweather

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView

/**
 * Component for wind arrow
 */

class WindArrow(context: Context?) : FrameLayout(context) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.wind_arrow, this)

        val arrowImageView: ImageView = findViewById(R.id.windArrowImageView) as ImageView
    }
}
