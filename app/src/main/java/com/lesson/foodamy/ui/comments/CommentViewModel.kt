package com.lesson.foodamy.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.CommentApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentApiRepository: CommentApiRepository,
    private val sharedPreferences: IPrefDefaultManager
    ) : BaseViewModel() {

    private val _responseComments: MutableLiveData<BaseResponse<ResponseComments>> =
        MutableLiveData()

    val responseComments: LiveData<BaseResponse<ResponseComments>>
        get() = _responseComments

    val commentText: MutableLiveData<String> = MutableLiveData("")
    var recipeID = -1

    fun addComment() = viewModelScope.launch {
        val token = sharedPreferences.getToken()
        if (token==""){
            showAlertDialog(R.string.need_login_text,CommentFragmentDirections.actionCommentFragmentToLoginFragment())
        }else{
            val response = commentApiRepository.requestAddComments(recipeID,sharedPreferences.getToken(),commentText.value.toString().replace("\"", ""))
            when(response){
                is BaseResponse.Error -> {
                    showMessage(response.error.error.toString())
                }
                is BaseResponse.Success -> {
                    getCommentsOfRecipe()
                    showMessage("Success Comment Process")
                }
                null -> {
                }
            }
        }
    }


    fun getCommentsOfRecipe() = viewModelScope.launch {
        _responseComments.value  = commentApiRepository.requestComments(recipeID)
    }
}