package com.lesson.foodamy.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseUser
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.repository.AuthApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authAPIRepository: AuthApiRepository) :
    BaseViewModel() {
    private var _responseMessage: MutableLiveData<BaseResponse<ResponseUser>> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    val responseMessage: LiveData<BaseResponse<ResponseUser>>
        get() = _responseMessage


    fun login() = viewModelScope.launch {

        val authData = AuthData(email.value!!, password.value!!)
        if (isLoginFieldsValid(authData)) {
            _responseMessage.value = authAPIRepository.requestLogin(authData)
            navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
        }
    }

    fun isLoginFieldsValid(userAuth: AuthData): Boolean {
        return when {
            userAuth.email.isEmpty() -> {
                showErrorMessage(R.string.empty_email_blank)
                false
            }
            userAuth.password.isEmpty() -> {
                showErrorMessage(R.string.empty_password_blank)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(userAuth.email).matches() -> {
                showErrorMessage(R.string.wrong_format_email)
                false
            }
            else -> true
        }
    }
}