package com.lesson.foodamy.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.model.*
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.repository.AuthApiRepository
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.preferences.IPrefDefaultManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authAPIRepository: AuthApiRepository,
    private val loginSharedPreferences: IPrefDefaultManager,
) : BaseViewModel() {

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    fun login() = viewModelScope.launch {

        val authData = AuthData(email.value!!, password.value!!)
        if (isLoginFieldsValid(authData)) {
            val response = authAPIRepository.requestLogin(authData)

            when (response) {
                is BaseResponse.Error -> {
                    showMessage(response.error.error.toString())
                }
                is BaseResponse.Success -> {
                    loginSharedPreferences.setUserInfo(response.data.user?.toResult()!!)
                    loginSharedPreferences.saveLogin(isLogged = true)
                    navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                }
            }
        }

    }

    fun navigateToSignUp() {
        navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    fun navigateToMain() {
        navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
    }

    private fun isLoginFieldsValid(userAuth: AuthData): Boolean {
        return when {
            userAuth.email.isEmpty() -> {
                showMessage(R.string.empty_email_blank)
                false
            }
            userAuth.password.isEmpty() -> {
                showMessage(R.string.empty_password_blank)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(userAuth.email).matches() -> {
                showMessage(R.string.wrong_format_email)
                false
            }
            else -> true
        }
    }
}