package com.lesson.foodamy.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.*
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
    private val sharedPreferences: IPrefDefaultManager
    ) : BaseViewModel() {

    private var token: String = ""

    init {
        token = sharedPreferences.getToken()
    }

    var comments: MutableLiveData<ArrayList<Comment>> = MutableLiveData()
    var recipeID = -1

    var commentText =  MutableLiveData<String>()

    fun addComment() = viewModelScope.launch {
        if (token==""){
            showAlertDialog(R.string.need_login_text,
                CommentFragmentDirections.actionCommentFragmentToLoginFragment())
        }else{
/*            print(commentText.value)
            comments.postValue(arrayListOf(Comment("1sn",5,commentText.value.toString(),User(
                "",
                "",
                "",
            0,
           0,
            0,
           0,
            null,
            "",
            false,
                false,
            0,
            "tr",
            0,
            "kemalaki",
           0,
           "ivj",
            "",
            "kemalaki",
            ""
            ))))*/
            when(val response = recipesAPIRepository.requestAddComments(recipeID,
                token,
                commentText.value.toString()))
                {

                is BaseResponse.Error -> {
                    showMessage(response.error.error.toString())
                }
                is BaseResponse.Success -> {
                    commentText.value = ""
                    getCommentsOfRecipe()
                    showMessage("Success Comment Process")
                }
                null -> {
                }
            }
        }
    }


    fun getCommentsOfRecipe() = viewModelScope.launch {
          when(val response = recipesAPIRepository.requestComments(recipeID)){
              is BaseResponse.Error -> {
                  response.error.error?.let {
                      showMessage(it)
                  }
              }
              is BaseResponse.Success -> {
                  response.data.data.let {
                      comments.value = it
                  }
              }
              null -> {
                  showMessage(R.string.null_error)
              }
          }
    }

    fun deleteComment(commentID: Int) = viewModelScope.launch {
        when(val response = recipesAPIRepository.requestDeleteComment(recipeID,commentID,token)){
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                getCommentsOfRecipe()
                showMessage(response.data.message)
            }
            null -> {
                showMessage(R.string.null_error)
            }
        }
    }

    fun editComment(comment: Comment) {
        navigate(CommentFragmentDirections.actionCommentFragmentToEditCommentFragment(recipeID,comment))
    }
}