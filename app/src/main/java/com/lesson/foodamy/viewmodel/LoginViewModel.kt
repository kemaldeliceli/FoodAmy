package com.lesson.foodamy.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.model.*
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authAPIRepository: AuthAPIRepository) : BaseViewModel() {
    private var _responseMessage: MutableLiveData<BaseResponse<ResponseUser>> = MutableLiveData()
    val _email: MutableLiveData<String> = MutableLiveData("")
    val _password: MutableLiveData<String> = MutableLiveData("")

    val responseMessage: LiveData<BaseResponse<ResponseUser>>
        get() = _responseMessage


    fun login() = viewModelScope.launch {

        val authData = AuthData(_email.value!!,_password.value!!)
        if (isLoginFieldsValid(authData)){
            _responseMessage.value = authAPIRepository.requestLogin(authData)
        }
    }

    fun isLoginFieldsValid(userAuth: AuthData): Boolean {

        if (userAuth.email.isEmpty()) {
            showErrorMessage(R.string.empty_email_blank)
            return false
        }
        if (userAuth.password.isEmpty()) {
            showErrorMessage(R.string.empty_password_blank)
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userAuth.email).matches()) {
            showErrorMessage(R.string.wrong_format_email)
            return false
        }
        return true
    }
}