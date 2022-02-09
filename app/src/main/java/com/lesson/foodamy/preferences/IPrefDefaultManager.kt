package com.lesson.foodamy.preferences

import com.lesson.foodamy.model.UserInfo

interface IPrefDefaultManager {
    fun isAppFirstOpen():Boolean
    fun saveAppOpened()
    fun setUserInfo(userInfo: UserInfo)
    fun getUserInfo():UserInfo?
}