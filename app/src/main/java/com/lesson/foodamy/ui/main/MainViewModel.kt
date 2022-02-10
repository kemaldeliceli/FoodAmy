package com.lesson.foodamy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseLogout
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginSharedPreferences: IPrefDefaultManager,
                                        private val authApiRepository: AuthApiRepository
) : BaseViewModel() {
    private val _responseLogout : MutableLiveData<BaseResponse<ResponseLogout>> = MutableLiveData()
    val responseLogout: LiveData<BaseResponse<ResponseLogout>>
        get() = _responseLogout

    fun logOut() = viewModelScope.launch{
        _responseLogout.value = authApiRepository.requestLogout()
    }

}