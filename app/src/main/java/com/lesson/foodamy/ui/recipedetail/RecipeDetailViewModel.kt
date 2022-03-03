package com.lesson.foodamy.ui.recipedetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.Comments
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

        when(val response = recipesAPIRepository.requestRecipeByID(recipeID.value!!)){
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                recipeInfo.value = response.data!!
                isLiked.postValue(recipeInfo.value!!.isLiked!!)
                likeCount.postValue(recipeInfo.value!!.likeCount!!)

                isFollowing.postValue(recipeInfo.value!!.user?.isFollowing!!)
                followedCount.postValue(recipeInfo.value!!.user?.followedCount!!)
            }
            null -> {
                showMessage(R.string.null_error)
            }
        }
    }


     fun getCommentsOfRecipe() = viewModelScope.launch {

         when (val response = recipesAPIRepository.requestComments(recipeID.value!!)) {
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                comments.value = response.data.data
                if(comments.value!!.size!=0){
                    firstComment.value = response.data.data[0]
                }
            }
            null -> {
            }
        }
    }


    fun goToCommentPage() {
        navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToCommentFragment(
            comments.value!!,recipeID.value!!))
    }

    fun likeRecipe() = viewModelScope.launch {

            if (isLiked.value==false) {
                when (val response =
                    recipesAPIRepository.requestLikeRecipe(recipeID.value!!)) {
                    is BaseResponse.Error -> {
                        if (response.error.code=="auth.token"){
                            showAlertDialog(R.string.need_login_text,
                                RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToLoginFragment())
                        }
                    }
                    is BaseResponse.Success -> {
                        isLiked.postValue(true)
                        likeCount.postValue(likeCount.value?.plus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }
            }else{
                when(val response = recipesAPIRepository.requestDeleteLikeRecipe(recipeID.value!!)){
                    is BaseResponse.Error -> {
                        if (response.error.code.equals("auth.token")){
                            showLoginDialog()
                        }
                    }
                    is BaseResponse.Success -> {
                        isLiked.value = false
                        likeCount.postValue(likeCount.value?.minus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }

            }
        }

    private fun showLoginDialog() {
        showAlertDialog(R.string.need_login_text,
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToLoginFragment())
    }


    fun followUser()=viewModelScope.launch {

            if (isFollowing.value==false){
                when (val response =
                    recipesAPIRepository.requestFollowUser(recipeInfo.value?.user?.id!!)) {
                    is BaseResponse.Error -> {
                        if (response.error.code=="auth.token"){
                            showLoginDialog()
                        }
                    }
                    is BaseResponse.Success -> {
                        isFollowing.postValue(true)
                        followedCount.postValue(followedCount.value?.plus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
                }
            }else{
                when (val response =
                recipesAPIRepository.requestDeleteFollowUser(recipeInfo.value?.user?.id!!)) {
                    is BaseResponse.Error -> {
                        if (response.error.code=="auth.token"){
                            showLoginDialog()
                        }
                    }
                    is BaseResponse.Success -> {
                        isFollowing.postValue(false)
                        followedCount.postValue(followedCount.value?.minus(1))
                    }
                    null -> {
                        showMessage(R.string.null_error)
                    }
            }

            }

        }
    }


