package com.lesson.foodamy.ui.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.dataclass.RegisterData
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authAPIRepository: AuthAPIRepository) :
    BaseViewModel() {
    val username: MutableLiveData<String> = MutableLiveData("")
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    fun register() {
        val registerData = RegisterData(email.value!!, password.value!!, username.value!!)

        if (isRegisterFieldsValid(registerData)) {
            sendRequest(
                request = { authAPIRepository.requestRegister(registerData) },
                success = { navigateToLogin() },
                loadingVal = true
            )
        }
    }

    private fun isRegisterFieldsValid(registerData: RegisterData): Boolean {
        return when {
            registerData.email.isEmpty() -> {
                showMessage(R.string.empty_email_blank)
                false
            }
            registerData.password.isEmpty() -> {
                showMessage(R.string.empty_password_blank)
                false
            }
            registerData.username.isEmpty() -> {
                showMessage(R.string.empty_username_blank)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(registerData.email).matches() -> {
                showMessage(R.string.wrong_format_email)
                false
            }
            else -> true
        }
    }

    fun navigateToLogin() {
        navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}
