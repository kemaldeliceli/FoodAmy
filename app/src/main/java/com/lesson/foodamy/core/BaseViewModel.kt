package com.lesson.foodamy.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lesson.foodamy.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    private var _errorMessage: SingleLiveEvent<Int> = SingleLiveEvent()
    
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    fun showErrorMessage(error:Int){
        _errorMessage.value = error
    }
}