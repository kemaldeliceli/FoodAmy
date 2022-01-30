package com.lesson.foodamy.Preferences

import com.lesson.foodamy.model.UserInfo

interface IPrefDefaultManager {
    fun isAppFirstOpen():Boolean
    fun saveAppOpened()
    fun setUserInfo(`object`: UserInfo)
    fun getUserInfo():UserInfo
}