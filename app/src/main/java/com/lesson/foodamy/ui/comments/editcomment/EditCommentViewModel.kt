package com.lesson.foodamy.ui.comments.editcomment


import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCommentViewModel @Inject constructor(private val recipesAPIRepository: RecipesAPIRepository,
private val sharedPreferences: IPrefDefaultManager
                                               ) : BaseViewModel(){
    var recipeID = 0
    var comment : Comment?=null

    var commentText = MutableLiveData<String>()


    fun editComment() = viewModelScope.launch {
        when(val response = recipesAPIRepository.
            requestEditComment(recipeID,comment?.id!!,commentText.value.toString())){
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                showMessage(response.data.message)
            }
            null -> {
                showMessage(R.string.null_error)
            }
        }
    }
}