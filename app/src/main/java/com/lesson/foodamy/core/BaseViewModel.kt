package com.lesson.foodamy.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.lesson.foodamy.R
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.utils.SingleLiveEvent
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val event = SingleLiveEvent<BaseViewEvent>()
    val loading = MutableLiveData<Boolean>()

    fun sendEvent(ev: BaseViewEvent) {
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
        loadingVal: Boolean = false
    ) = viewModelScope.launch {

        loading.postValue(loadingVal)
        try {
            val response = request.invoke()
            success.invoke(response)
            loading.postValue(false)
        } catch (e: Exception) {
            loading.postValue(false)
            error?.invoke(handleException(e))
        }
    }

    private fun handleException(e: Exception) =
        when (e) {
            is BaseException -> {
                if (e.code == 403)
                    AuthException(id = R.string.need_login_text)
                else
                    e
            }
            else -> e
        }

    class AuthException(val msg: String? = "Auth Error", val id: Int? = 0) : java.lang.Exception()

}
