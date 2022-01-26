package com.lesson.foodamy.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseUser
import com.lesson.foodamy.model.dataclass.RegisterData
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authAPIRepository: AuthAPIRepository) : BaseViewModel(){
    private var _responseMessage: MutableLiveData<BaseResponse<ResponseUser>> = MutableLiveData()
    val username : MutableLiveData<String> = MutableLiveData("")
    val email : MutableLiveData<String>  = MutableLiveData("")
    val password: MutableLiveData<String>  = MutableLiveData("")

    val responseMessage: LiveData<BaseResponse<ResponseUser>>
        get() = _responseMessage

    fun register() = viewModelScope.launch {
        val registerData = RegisterData(email.value!!,password.value!!,username.value!!)
        if (isRegisterFieldsValid(registerData)) {
            _responseMessage.value = authAPIRepository.requestRegister(registerData)
        }
    }

    fun isRegisterFieldsValid(registerData: RegisterData): Boolean {
        return when{
            registerData.email.isEmpty()-> {
                showErrorMessage(R.string.empty_email_blank)
                false
            }
            registerData.password.isEmpty()-> {
                showErrorMessage(R.string.empty_password_blank)
                false
            }
            registerData.username.isEmpty()-> {
                showErrorMessage(R.string.empty_username_blank)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(registerData.email).matches()-> {
                showErrorMessage(R.string.wrong_format_email)
                false
            }
            else -> true
        }

    }
}