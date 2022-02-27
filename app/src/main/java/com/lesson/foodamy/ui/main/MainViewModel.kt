package com.lesson.foodamy.ui.main


import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginSharedPreferences: IPrefDefaultManager,
                                        private val authApiRepository: AuthApiRepository
) : BaseViewModel() {

    fun logOut() = viewModelScope.launch{
        when(val response = authApiRepository.requestLogout()){
            is BaseResponse.Success->{
                showMessage(R.string.successfull_logout)
                loginSharedPreferences.saveLogin(isLogged = false)
                loginSharedPreferences.setToken("")
                navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
            }
            is BaseResponse.Error->{
                showMessage(response.error.error.toString())
            }
        }

    }

}