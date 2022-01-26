package com.lesson.foodamy.Preferences

interface IPrefDefaultManager {
    fun isAppFirstOpen():Boolean
    fun saveAppOpened()
}