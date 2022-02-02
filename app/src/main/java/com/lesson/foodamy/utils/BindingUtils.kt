package com.lesson.foodamy.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {

    @JvmStatic
    @BindingAdapter("app:updateVisibility")
    fun updateVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(url)
            .into(view)    }

}