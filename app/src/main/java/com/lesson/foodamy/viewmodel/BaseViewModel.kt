package com.lesson.foodamy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private var _errorMessage: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: LiveData<Int>
        get() = _errorMessage

    fun showErrorMessage(error:Int){
        _errorMessage.value = error
    }
}