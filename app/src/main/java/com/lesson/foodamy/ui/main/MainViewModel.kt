package com.lesson.foodamy.ui.main

import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.ResponseLogout
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginSharedPreferences: IPrefDefaultManager,
    private val authAPIRepository: AuthAPIRepository,
) : BaseViewModel() {

    fun logOut() = viewModelScope.launch {
        try {
         val response = authAPIRepository.requestLogout()
            if(response.code=="success"){
                //todo navigatae
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
