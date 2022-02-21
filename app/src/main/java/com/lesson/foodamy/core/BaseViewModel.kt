package com.lesson.foodamy.core

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.lesson.foodamy.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    val event = SingleLiveEvent<BaseViewEvent>()

    fun sendEvent(ev: BaseViewEvent){
        event.postValue(ev)
    }

    fun navigate(directions: NavDirections){
        sendEvent(BaseViewEvent.Navigate(directions))
    }

    fun showMessage(msg: Any){
        sendEvent(BaseViewEvent.ShowMessage(msg))
    }
    fun showAlertDialog(msg: Any, directions: NavDirections){
        sendEvent(BaseViewEvent.ShowAlertDialog(msg, directions))
    }
    fun popBackStack(){
        sendEvent(BaseViewEvent.PopBackStack)
    }

}