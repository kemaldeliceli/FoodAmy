package com.lesson.foodamy.ui.recipedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseComment
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.Comments
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.dataclass.BaseException
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.model.recipe_detail_info.ResponseFollow
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
) : BaseViewModel() {

    private var comments: MutableLiveData<Comments> = MutableLiveData()

    var recipeID: MutableLiveData<Int> = MutableLiveData(5)
    val firstComment: MutableLiveData<Comment> = MutableLiveData()
    var recipeInfo: MutableLiveData<RecipeDetailInfo> = MutableLiveData()

    val isLiked: MutableLiveData<Boolean> = MutableLiveData(false)
    val likeCount: MutableLiveData<Int> = MutableLiveData(0)

    val isFollowing: MutableLiveData<Boolean> = MutableLiveData(false)
    val followedCount: MutableLiveData<Int> = MutableLiveData(0)

    fun getRecipeInfoByID() = viewModelScope.launch {

        try {
            when (val response = recipesAPIRepository.requestRecipeByID(recipeID.value!!)) {

                is RecipeDetailInfo -> {
                    recipeInfo.value = response!!
                    isLiked.postValue(recipeInfo.value!!.isLiked!!)
                    likeCount.postValue(recipeInfo.value!!.likeCount!!)

                    isFollowing.postValue(recipeInfo.value!!.user?.isFollowing!!)
                    followedCount.postValue(recipeInfo.value!!.user?.followedCount!!)
                }
                null -> {
                    showMessage(R.string.null_error)
                }
            }

        }catch (e:Exception){
            when(e){
                is BaseException -> {
                    showMessage(e.error.toString())
                }
            }
        }
    }

    fun getCommentsOfRecipe() = viewModelScope.launch {

        try {
            when (val response = recipesAPIRepository.requestComments(recipeID.value!!)) {
                is ResponseComments -> {
                    comments.value = response.data
                    if (comments.value!!.size != 0) {
                        firstComment.value = response.data[0]
                    }
                }
                null -> {
                }
            }


        }catch (e:Exception){
            showMessage(e.message.toString())
        }
    }

    fun goToCommentPage() {
        navigate(
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToCommentFragment(
                comments.value!!, recipeID.value!!
            )
        )
    }

    fun likeRecipe() = viewModelScope.launch {

        if (isLiked.value == false) {

            try {
                when (recipesAPIRepository.requestLikeRecipe(recipeID.value!!)
                ) {
                    is ResponseLike -> {
                        isLiked.postValue(true)
                        likeCount.postValue(likeCount.value?.plus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }
            }catch (e:Exception){
                when(e){
                    is BaseException -> {
                        if (e.code == 403) {
                           showLoginDialog()
                        }
                    }
                }
            }

        } else {
            try {
                when (recipesAPIRepository.requestDeleteLikeRecipe(recipeID.value!!)) {

                    is ResponseLike -> {
                        isLiked.value = false
                        likeCount.postValue(likeCount.value?.minus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }

            }catch (e: Exception){
                when(e){
                    is BaseException->{
                        if(e.code==403)
                            showLoginDialog()
                    }
                }
            }

        }
    }

    private fun showLoginDialog() {
        showAlertDialog(
            R.string.need_login_text,
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToLoginFragment()
        )
    }

    fun followUser() = viewModelScope.launch {

        if (isFollowing.value == false) {
            try {
                when (
                        recipesAPIRepository.requestFollowUser(recipeInfo.value?.user?.id!!)
                ) {

                    is ResponseFollow -> {
                        isFollowing.postValue(true)
                        followedCount.postValue(followedCount.value?.plus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }
            }catch (e:Exception){
                when(e){
                    is BaseException ->{
                        if (e.code==403){
                            showLoginDialog()

                        }
                    }
                }

            }

        } else {
            try {
                when (recipesAPIRepository.requestDeleteFollowUser(recipeInfo.value?.user?.id!!)) {
                    is ResponseFollow -> {
                        isFollowing.postValue(false)
                        followedCount.postValue(followedCount.value?.minus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }
            }catch (e:Exception){
                when(e){
                    is BaseException -> {
                        if (e.code==403){
                            showLoginDialog()
                        }
                    }
                }
            }

        }
    }
}
