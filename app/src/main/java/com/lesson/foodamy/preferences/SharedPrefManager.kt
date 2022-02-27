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
        private const val userToken = "userToken"
    }

    override fun saveAppOpened(){
            sharedPreferences.edit(commit = true){
                putBoolean(firstTimeAppOpen,true)
            }
    }



    override fun isAppFirstOpen() = sharedPreferences.getBoolean(firstTimeAppOpen,false)

    override fun setUserInfo(user: UserInformation) {
        val jsonString = GsonBuilder().create().toJson(user)

        sharedPreferences.edit().putString(userInfoObject, jsonString).apply()
    }

    override fun getUserInfo(): UserInformation? {
        val value = sharedPreferences.getString(userInfoObject, null)
        return GsonBuilder().create().fromJson(value, UserInformation::class.java)
    }

    override fun isLoggedIn() = sharedPreferences.getBoolean(isLoggedIn,false)

    override fun saveLogin(isLogged:Boolean){
        sharedPreferences.edit(commit = true){
            putBoolean(isLoggedIn,isLogged)
        }
    }

    override fun setToken(token: String) {
        sharedPreferences.edit(commit = true){
            putString(userToken,token)
        }
    }

    override fun getToken(): String {
        return sharedPreferences.getString(userToken,"")!!
    }


}