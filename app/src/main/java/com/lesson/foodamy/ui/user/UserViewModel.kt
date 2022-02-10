package com.lesson.foodamy.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.UserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val loginSharedPreferences: IPrefDefaultManager): BaseViewModel() {

    private  var _userInfo:MutableLiveData<UserInformation> = MutableLiveData()
    private var _isLogged: MutableLiveData<Boolean> = MutableLiveData()

    val userInfo : LiveData<UserInformation>
        get() = _userInfo
    val isLogged: LiveData<Boolean>
        get() = _isLogged

    fun getUserInfoFromApi() = viewModelScope.launch{
        _userInfo.value = loginSharedPreferences.getUserInfo()
    }
    fun checkLogin() = viewModelScope.launch {
        _isLogged.value = loginSharedPreferences.isLoggedIn()
    }

}