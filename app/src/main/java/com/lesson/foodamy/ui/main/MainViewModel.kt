package com.lesson.foodamy.ui.main

import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.ResponseLogout
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginSharedPreferences: IPrefDefaultManager,
    private val authAPIRepository: AuthAPIRepository,
) : BaseViewModel() {

    fun logOut() = viewModelScope.launch {
        try {
            when (authAPIRepository.requestLogout()) {
                is ResponseLogout -> {
                    showMessage(R.string.successfull_logout)
                    loginSharedPreferences.saveLogin(isLogged = false)
                    loginSharedPreferences.setToken("")
                    loginSharedPreferences.setUserInfo(null)
                    navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
                }
            }
        } catch (e: Exception) {
            when (e) {
                is BaseException -> {
                    showMessage(e.error.toString())
                }

            }

        }

    }
}
