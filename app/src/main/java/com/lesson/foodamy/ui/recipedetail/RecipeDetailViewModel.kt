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

    fun getRecipeInfoByID() {
        sendRequest(
            request = { recipesAPIRepository.requestRecipeByID(recipeID.value!!) },
            success = { recipeDetailInfo ->
                recipeInfo.value = recipeDetailInfo!!
                isLiked.postValue(recipeInfo.value!!.isLiked!!)
                likeCount.postValue(recipeInfo.value!!.likeCount!!)

                isFollowing.postValue(recipeInfo.value!!.user?.isFollowing!!)
                followedCount.postValue(recipeInfo.value!!.user?.followedCount!!)
            },
            loadingVal = true
        )
    }

    fun getCommentsOfRecipe() {

        sendRequest(
            request = { recipesAPIRepository.requestComments(recipeID.value!!) },
            success = { responseComments ->
                comments.value = responseComments?.data
                if (comments.value!!.size != 0) {
                    firstComment.value = responseComments!!.data[0]
                }
            },
            loadingVal = true

        )
    }

    fun goToCommentPage() {
        navigate(
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToCommentFragment(
                comments.value!!, recipeID.value!!
            )
        )
    }

    fun likeRecipe() {

        if (isLiked.value == false) {
            sendRequest(
                request = { recipesAPIRepository.requestLikeRecipe(recipeID.value!!) },
                success = {
                    isLiked.postValue(true)
                    likeCount.postValue(likeCount.value?.plus(1))
                },
                loginDialog = {
                    showLoginDialog()
                },
                loadingVal = false
            )

        } else {
            sendRequest(
                request = { recipesAPIRepository.requestDeleteLikeRecipe(recipeID.value!!) },
                success = {
                    isLiked.value = false
                    likeCount.postValue(likeCount.value?.minus(1))
                },
                loginDialog = { showLoginDialog() },
                loadingVal = false
            )

        }

    }


    fun followUser() {

        if (isFollowing.value == false) {
            sendRequest(
                request = { recipesAPIRepository.requestFollowUser(recipeInfo.value?.user?.id!!) },
                success = {
                    isFollowing.postValue(true)
                    followedCount.postValue(followedCount.value?.plus(1))
                },
                loginDialog = {
                    showLoginDialog()
                },
                loadingVal = false

            )

        } else {
            sendRequest(
                request = { recipesAPIRepository.requestDeleteFollowUser(recipeInfo.value?.user?.id!!) },
                success = {
                    isFollowing.postValue(false)
                    followedCount.postValue(followedCount.value?.minus(1))
                },
                loginDialog = { showLoginDialog() },
                loadingVal = false
            )

        }
    }

    private fun showLoginDialog() {
        showAlertDialog(
            R.string.need_login_text,
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToLoginFragment()
        )
    }
}
