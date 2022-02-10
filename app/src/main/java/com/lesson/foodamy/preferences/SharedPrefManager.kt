package com.lesson.foodamy.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.lesson.foodamy.model.UserInformation

class SharedPrefManager(private val sharedPreferences: SharedPreferences) : IPrefDefaultManager {

    companion object{
        private const val firstTimeAppOpen = "firstTimeAppOpen"
        private const val isLoggedIn="isLoggedIn"
        private const val userInfoObject = "userInfoObject"
    }

    override fun saveAppOpened(){
            sharedPreferences.edit(commit = true){
                putBoolean(firstTimeAppOpen,true)
            }
    }

    override fun isLoggedIn() = sharedPreferences.getBoolean(isLoggedIn,false)

    override fun saveLogin(isLogged:Boolean){
        sharedPreferences.edit(commit = true){
            putBoolean(isLoggedIn,isLogged)
        }
    }

    override fun isAppFirstOpen() = sharedPreferences.getBoolean(firstTimeAppOpen,false)

    override fun setUserInfo(user: UserInformation) {
        val jsonString = GsonBuilder().create().toJson(user)
        //Save that String in SharedPreferences
        sharedPreferences.edit().putString(userInfoObject, jsonString).apply()
    }

    override fun getUserInfo(): UserInformation? {
        val value = sharedPreferences.getString(userInfoObject, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, UserInformation::class.java)
    }


}