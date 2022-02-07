package com.lesson.foodamy.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.lesson.foodamy.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    private var _errorMessage: SingleLiveEvent<Int> = SingleLiveEvent()

    val event = SingleLiveEvent<BaseViewEvent>()

    fun sendEvent(ev: BaseViewEvent) {
        event.postValue(ev)
    }

    fun navigate(directions: NavDirections) {
        sendEvent(BaseViewEvent.Navigate(directions))
    }

    val errorMessage: LiveData<Int>
        get() = _errorMessage

    fun showErrorMessage(error: Int) {
        _errorMessage.value = error
    }
}