package com.lesson.foodamy.core

import androidx.navigation.NavDirections

sealed class BaseViewEvent{
    data class ShowMessage(val msg:Any) : BaseViewEvent()
    data class Navigate(val directions: NavDirections): BaseViewEvent()
}