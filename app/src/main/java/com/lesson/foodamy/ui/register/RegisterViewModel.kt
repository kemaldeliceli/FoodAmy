package com.lesson.foodamy.ui.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.dataclass.RegisterData
import com.lesson.foodamy.repository.AuthApiRepository
import com.lesson.foodamy.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authAPIRepository: AuthApiRepository) : BaseViewModel(){
    val username : MutableLiveData<String> = MutableLiveData("")
    val email : MutableLiveData<String>  = MutableLiveData("")
    val password: MutableLiveData<String>  = MutableLiveData("")

    fun register() = viewModelScope.launch {
        val registerData = RegisterData(email.value!!,password.value!!,username.value!!)
        if (isRegisterFieldsValid(registerData)) {
            val response = authAPIRepository.requestRegister(registerData)
            when(response){
                is BaseResponse.Success -> {
                    showMessage(R.string.success_register_process)
                    navigateToLogin()
                }
                is BaseResponse.Error -> {showMessage(response.error.error.toString())}
            }

        }
    }

    fun isRegisterFieldsValid(registerData: RegisterData): Boolean {
        return when{
            registerData.email.isEmpty()-> {
                showMessage(R.string.empty_email_blank)
                false
            }
            registerData.password.isEmpty()-> {
                showMessage(R.string.empty_password_blank)
                false
            }
            registerData.username.isEmpty()-> {
                showMessage(R.string.empty_username_blank)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(registerData.email).matches()-> {
                showMessage(R.string.wrong_format_email)
                false
            }
            else -> true
        }

    }
    fun navigateToLogin(){
        navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}