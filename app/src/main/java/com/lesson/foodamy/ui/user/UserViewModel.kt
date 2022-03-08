package com.lesson.foodamy.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.UserInformation
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val loginSharedPreferences: IPrefDefaultManager) : BaseViewModel() {

    val userInfo: MutableLiveData<UserInformation> = MutableLiveData()
    val isLogged: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getUserInformation()
        getLoginInformation()
    }

    fun getUserInformation() = viewModelScope.launch {
        userInfo.value = loginSharedPreferences.getUserInfo()
    }
    fun getLoginInformation() = viewModelScope.launch {
        isLogged.value = loginSharedPreferences.isLoggedIn()
    }
    fun navigateToLogin() {
        navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
    }
}
