package com.lesson.foodamy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodAmy : Application() {

    private lateinit var instance: FoodAmy

    override fun onCreate() {
        super.onCreate()
    }
}
