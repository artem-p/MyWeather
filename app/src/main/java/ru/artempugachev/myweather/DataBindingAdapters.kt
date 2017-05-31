package ru.artempugachev.myweather

import android.databinding.BindingAdapter
import android.widget.ImageView

class DataBindingAdapters {
    companion object {
        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageResource(imageView: ImageView, imageResource: Int) {
            imageView.setImageResource(imageResource)
        }
    }
}