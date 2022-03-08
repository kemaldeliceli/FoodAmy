package com.lesson.foodamy.ui.comments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.ResponseComment
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.repository.CommentPagingSource
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.services.RecipeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
    private val recipeService: RecipeService,
) : BaseViewModel() {

    var comments: MutableLiveData<PagingData<Comment>> = MutableLiveData()
    var recipeID = -1

    var commentText = MutableLiveData<String>()

    fun addComment() = viewModelScope.launch {
        try {
            when (recipesAPIRepository.requestAddComments(
                recipeID,
                commentText.value.toString())
            ) {

                is Comment -> {
                    commentText.value = ""
                    getListData()
                }
                null -> {
                }
            }
        } catch (e: Exception) {
            when (e) {
                is BaseException -> {
                    if (e.code == 403) {
                        showLoginDialog()
                    }
                }
            }
        }

    }

    private fun showLoginDialog() {
        showAlertDialog(
            R.string.need_login_text,
            CommentFragmentDirections.actionCommentFragmentToLoginFragment()
        )
    }

    fun getListData() {
        Pager(
            config = PagingConfig(pageSize = 24, maxSize = 200),
            pagingSourceFactory = {
                CommentPagingSource(
                    recipeService,
                    recipeID
                )
            }
        ).flow.let {
            viewModelScope.launch {
                it.cachedIn(viewModelScope).collect {
                    comments.postValue(it)
                }
            }
        }
    }

    fun deleteComment(commentID: Int) = viewModelScope.launch {
        try {
            when (recipesAPIRepository.requestDeleteComment(recipeID, commentID)) {

                is ResponseComment -> {
                    getListData()
                }
                null -> {
                    showMessage(R.string.null_error)
                }
            }
        } catch (e: Exception) {
            when (e) {
                is BaseException -> {
                    showMessage(e.error.toString())
                }
            }
        }

    }

    fun editComment(comment: Comment) {
        navigate(CommentFragmentDirections.actionCommentFragmentToEditCommentFragment(recipeID,
            comment))
    }
}
