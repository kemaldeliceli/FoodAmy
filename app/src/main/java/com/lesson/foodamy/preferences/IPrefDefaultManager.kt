package com.lesson.foodamy.preferences

import com.lesson.foodamy.model.UserInformation

interface IPrefDefaultManager {
    fun isAppFirstOpen(): Boolean
    fun saveAppOpened()

    fun isLoggedIn(): Boolean
    fun saveLogin(isLogged: Boolean)

    fun setUserInfo(userInfo: UserInformation?)
    fun getUserInfo(): UserInformation?

    fun setToken(token: String)
    fun getToken(): String
}
