package com.lesson.foodamy
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class LoginSharedPref {
    private var loginCounter = "loginCounter"
    private var sharedPreferences : SharedPreferences? = null;

    operator fun invoke(context: Context){
        sharedPreferences = context.getSharedPreferences(loginCounter,Context.MODE_PRIVATE)
    }

    fun saveAppOpened(){
        sharedPreferences?.edit(commit = true){
            putBoolean(loginCounter,true)
        }
    }
    fun getAppOpened() = sharedPreferences?.getBoolean(loginCounter,false)
}