package com.lesson.foodamy.ui.comments

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.exception.AuthException
import com.lesson.foodamy.repository.CommentPagingSource
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.services.RecipeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
    private val recipeService: RecipeService,
) : BaseViewModel() {

    val comments: MutableLiveData<PagingData<Comment>> = MutableLiveData()
    val commentText = MutableLiveData<String>("")
    private var recipeId = -1

    fun setRecipeId(recipeId: Int) {
        this.recipeId = recipeId
        getListData()
    }

    fun addComment() {
        sendRequest(
            request = {
                recipesAPIRepository.requestAddComments(
                    recipeId,
                    commentText.value.toString()
                )
            },
            success = {
                commentText.value = ""
                getListData()
            },
            error = { e ->
                if (e is AuthException) {
                    e.id?.let { showLoginDialog(it) }
                }
            }
        )
    }

    private fun showLoginDialog(@StringRes stringId: Int) {
        showAlertDialog(
            stringId,
            CommentFragmentDirections.actionCommentFragmentToLoginFragment()
        )
    }

    fun getListData() {
        sendRequest(
            request = {
                Pager(
                    config = PagingConfig(pageSize = 24, maxSize = 200),
                    pagingSourceFactory = {
                        CommentPagingSource(
                            recipeService,
                            recipeId
                        )
                    }
                ).flow
            },
            success = { pagingData ->
                pagingData.let {
                    viewModelScope.launch {
                        it.cachedIn(viewModelScope).collect {
                            comments.postValue(it)
                        }
                    }
                }
            },
            loadingVal = true

        )
    }

    fun deleteComment(commentID: Int) {
        sendRequest(
            request = { recipesAPIRepository.requestDeleteComment(recipeId, commentID) },
            success = { getListData() },
        )
    }

    fun navigateToEditComment(comment: Comment) {
        navigate(
            CommentFragmentDirections.actionCommentFragmentToEditCommentFragment(
                recipeId,
                comment
            )
        )
    }
}
