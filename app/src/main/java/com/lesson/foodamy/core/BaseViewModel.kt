package com.lesson.foodamy.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.lesson.foodamy.SingleLiveEvent
import com.lesson.foodamy.model.dataclass.BaseException
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

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
        request: suspend ()->T,
        success: ((T)->Unit),
        error: ((Exception)->Unit)?=null,
        loginDialog: (()->Unit)?=null,
        loadingVal: Boolean = false
    )= viewModelScope.launch {

        loading.postValue(loadingVal)
        try {
            val response = request.invoke()
            success.invoke(response)
            loading.postValue(false)
        }catch (e:Exception){
            loading.postValue(false)
            when(error){
                null -> {
                    handleException(e,loginDialog)
                }
            }
        }


    }

    private fun handleException(e:Exception, loginDialog: (() -> Unit)?) {
        when(e){
            is IOException -> {
                showMessage("No internet connection")
            }
            is BaseException ->{
                if (e.code==403){
                    loginDialog?.invoke()
                }else{
                    showMessage(e.error.toString())
                }
            }
        }
    }
}
