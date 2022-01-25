package com.lesson.foodamy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lesson.foodamy.SingleLiveEvent
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    private var _errorMessage: SingleLiveEvent<Int> = SingleLiveEvent()
    
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    fun showErrorMessage(error:Int){
        _errorMessage.value = error
    }
}