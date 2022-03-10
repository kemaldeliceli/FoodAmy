package com.lesson.foodamy.ui.main

import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authAPIRepository: AuthAPIRepository,
) : BaseViewModel() {

    fun logOut() = viewModelScope.launch {
        try {
            val response = authAPIRepository.requestLogout()
            if (response.code == "auth.logout") {
                navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
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
