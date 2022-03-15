package com.lesson.foodamy.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.model.exception.AuthException
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authAPIRepository: AuthAPIRepository,
    private val loginSharedPreferences: IPrefDefaultManager,
) : BaseViewModel() {

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    fun login() {
        val authData = AuthData(email.value!!, password.value!!)
        if (isLoginFieldsValid(authData)) {
            sendRequest(
                request = { authAPIRepository.requestLogin(authData) },
                success = { navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment()) },
                error= {ex->
                    if (ex is AuthException){
                        showMessage(ex.id!!)
                    }
                },
                loadingVal = true,
            )
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
