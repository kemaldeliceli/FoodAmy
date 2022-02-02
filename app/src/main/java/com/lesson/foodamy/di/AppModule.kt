package com.lesson.foodamy.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.preferences.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule{

    @Provides
    @Singleton
    fun getSharedPref(sharedPreferences: SharedPreferences) : IPrefDefaultManager = SharedPrefManager(sharedPreferences)

    @Provides
    @Singleton
    fun providesSharedPreference(application: Application): SharedPreferences{
        val prefName = "FodamyAppPref"
        return application.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

}