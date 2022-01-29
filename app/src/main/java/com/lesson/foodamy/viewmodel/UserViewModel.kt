package com.lesson.foodamy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.Preferences.IPrefDefaultManager
import com.lesson.foodamy.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val loginSharedPreferences: IPrefDefaultManager): BaseViewModel() {

    private  var _userInfo:MutableLiveData<UserInfo> = MutableLiveData()

    val userInfo : LiveData<UserInfo>
        get() = _userInfo

    fun getUserInfo() = viewModelScope.launch{
        _userInfo.value = loginSharedPreferences.getUserInfo()
    }

}