package com.lesson.foodamy.ui.comments.editcomment

import androidx.lifecycle.MutableLiveData
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCommentViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository
) : BaseViewModel() {
    private var recipeId = 0
    private var commentId = 0

    val commentText = MutableLiveData<String>()

    fun setComment(recipeId: Int, comment: Comment) {
        this.recipeId = recipeId
        this.commentId = comment.id!!
        this.commentText.postValue(comment.text)
    }

    fun editComment() {
        sendRequest(
            request = {
                recipesAPIRepository
                    .requestEditComment(recipeId, commentId, commentText.value.toString())
            },
            success = { showMessage(it.message) },
            error = { showMessage(R.string.null_error) }
        )
    }
}
