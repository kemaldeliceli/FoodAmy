package com.lesson.foodamy.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.ResponseUser
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.model.toResult
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authAPIRepository: AuthAPIRepository,
    private val loginSharedPreferences: IPrefDefaultManager,
) : BaseViewModel() {

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    fun login() = viewModelScope.launch {
        val authData = AuthData(email.value!!, password.value!!)
        if (isLoginFieldsValid(authData)) {
            try {
                when (val response = authAPIRepository.requestLogin(authData)) {
                    else -> {

                        navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                    }
                }
            } catch (e: Exception) {
                if (e is BaseException) {
                    showMessage(e.error.toString())
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
