package com.lesson.foodamy.Preferences

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefManager(private val sharedPreferences: SharedPreferences) : IPrefDefaultManager {

    companion object{
        private const val firstTimeAppOpen = "firstTimeAppOpen"
    }

    override fun saveAppOpened(){
            sharedPreferences.edit(commit = true){
                putBoolean(firstTimeAppOpen,true)
            }
    }

    override fun isAppFirstOpen() = sharedPreferences.getBoolean(firstTimeAppOpen,false)


}