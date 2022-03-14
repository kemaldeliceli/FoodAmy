package com.lesson.foodamy.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.lesson.foodamy.R
import com.lesson.foodamy.model.exception.AuthException
import com.lesson.foodamy.model.exception.BaseException
import com.lesson.foodamy.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.io.IOException

open class BaseViewModel : ViewModel() {

    val event = SingleLiveEvent<BaseViewEvent>()
    val loading = MutableLiveData(false)

    private fun sendEvent(ev: BaseViewEvent) {
        event.postValue(ev)
    }

    fun navigate(directions: NavDirections) {
        sendEvent(BaseViewEvent.Navigate(directions))
    }

    fun showMessage(msg: Any) {
        sendEvent(BaseViewEvent.ShowMessage(msg))
    }

    fun showAlertDialog(msg: Any, directions: NavDirections) {
        sendEvent(BaseViewEvent.ShowAlertDialog(msg, directions))
    }

    fun popBackStack() {
        sendEvent(BaseViewEvent.PopBackStack)
    }

    fun <T> sendRequest(
        request: suspend () -> T,
        success: ((T) -> Unit),
        error: ((Exception) -> Unit)? = null,
        loadingVal: Boolean = false,
    ) = viewModelScope.launch {

        loading.postValue(loadingVal)
        try {
            val response = request.invoke()
            success.invoke(response)
            loading.postValue(false)
        } catch (e: Exception) {
            loading.postValue(false)
            if (error == null)
                handleException(e)
            else
                error.invoke(handleException(e))
        }
    }

    private fun handleException(e: Exception) =
        when (e) {
            is BaseException -> {
                if (e.code == 403) {
                    showMessage(e.error.toString())
                    AuthException(id = R.string.need_login_text)
                } else {
                    showMessage(e.error.toString())
                    e
                }
            }
            is IOException -> {
                showMessage(R.string.no_internet_connection_text)
                IOException()
            }
            else -> {
                showMessage(e.message.toString())
                e
            }
        }
}
