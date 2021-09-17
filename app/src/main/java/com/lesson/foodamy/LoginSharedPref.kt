package com.lesson.foodamy
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class LoginSharedPref {

    companion object{
        private const val firstTimeAppOpen = "firstTimeAppOpen"
    }

    private lateinit var sharedPreferences : SharedPreferences

    operator fun invoke(context: Context){
        sharedPreferences = SharedPrefHelper.invoke(context, firstTimeAppOpen)!!
    }

    fun saveAppOpened(){
        sharedPreferences?.edit(commit = true){
            putBoolean(firstTimeAppOpen,true)
        }
    }
    fun isAppFirstOpen() = sharedPreferences?.getBoolean(firstTimeAppOpen,false)
}