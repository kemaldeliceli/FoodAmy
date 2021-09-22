package com.lesson.foodamy

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper private constructor(){
    companion object {
        var sharedPref: SharedPreferences? = null

        operator fun invoke(context: Context,sharedPreferencesName: String) = synchronized(this){
            if (sharedPref==null) {
                sharedPref = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
            }
            sharedPref
        }
    }
}