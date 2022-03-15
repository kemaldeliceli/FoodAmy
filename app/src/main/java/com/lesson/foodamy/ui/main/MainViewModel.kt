package com.lesson.foodamy.ui.main

import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authAPIRepository: AuthAPIRepository,
) : BaseViewModel() {

    fun logOut() {
        sendRequest(
            request = { authAPIRepository.requestLogout() },
            success = { navigate(MainFragmentDirections.actionMainFragmentToLoginFragment()) },
            loadingVal = true
        )
    }
}
