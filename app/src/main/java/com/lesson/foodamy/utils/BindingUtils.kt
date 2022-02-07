package com.lesson.foodamy.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.lesson.foodamy.R

object BindingUtils {

    @JvmStatic
    @BindingAdapter("app:updateVisibility")
    fun updateVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
    @JvmStatic
    @BindingAdapter("app:imageUrl","app:placeHolder")
    fun loadImage(view: ImageView, url: String?,placeholder: Drawable) {
        println(url)
        when {
            url.isNullOrEmpty() -> {
                view.setImageDrawable(placeholder)
            }
            else -> {
                Glide.with(view.context).load(url)
                    .priority(Priority.HIGH)
                    .into(view)
            }
        }
    }

}