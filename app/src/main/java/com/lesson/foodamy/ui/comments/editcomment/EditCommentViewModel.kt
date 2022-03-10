package com.lesson.foodamy.ui.comments.editcomment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.ResponseComment
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EditCommentViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
    private val sharedPreferences: IPrefDefaultManager
) : BaseViewModel() {
    var recipeID = 0
    var comment: Comment? = null

    var commentText = MutableLiveData<String>()

    fun editComment() = viewModelScope.launch {
        try {
            when (
                val response = recipesAPIRepository
                    .requestEditComment(recipeID, comment?.id!!, commentText.value.toString())
            ) {
                is ResponseComment -> {
                    showMessage(response.message)
                }
                null -> {
                    showMessage(R.string.null_error)
                }
            }
        } catch (e: Exception) {
            when (e) {
                is BaseException -> {
                    showMessage(e.message.toString())
                }
            }
        }
    }
}
