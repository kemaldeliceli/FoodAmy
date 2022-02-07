package com.lesson.foodamy.core

import androidx.navigation.NavAction
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections

/**
 * @author Mert Gölcü
 * @date 7.02.2022
 */

sealed class BaseViewEvent {
    data class ShowError(val msg: String) : BaseViewEvent()
    data class Navigate(val direction: NavDirections) : BaseViewEvent()
}