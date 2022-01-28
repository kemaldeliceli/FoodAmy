package com.lesson.foodamy.Preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.lesson.foodamy.model.UserInfo

class SharedPrefManager(private val sharedPreferences: SharedPreferences) : IPrefDefaultManager {

    companion object{
        private const val firstTimeAppOpen = "firstTimeAppOpen"
        private const val userInfoObject = "userInfoObject"
    }

    override fun saveAppOpened(){
            sharedPreferences.edit(commit = true){
                putBoolean(firstTimeAppOpen,true)
            }
    }



    override fun isAppFirstOpen() = sharedPreferences.getBoolean(firstTimeAppOpen,false)

    override fun setUserInfo(user: UserInfo) {
        val jsonString = GsonBuilder().create().toJson(user)
        //Save that String in SharedPreferences
        sharedPreferences.edit().putString(userInfoObject, jsonString).apply()
    }

    override fun getUserInfo(): UserInfo {
        val value = sharedPreferences.getString(userInfoObject, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, UserInfo::class.java)
    }


}