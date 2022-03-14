package com.lesson.foodamy.ui.recipedetail

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.Comments
import com.lesson.foodamy.model.exception.AuthException
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
) : BaseViewModel() {

    private var comments: MutableLiveData<Comments> = MutableLiveData()

    private var recipeID: Int = -1

    val firstComment: MutableLiveData<Comment> = MutableLiveData()
    var recipeInfo: MutableLiveData<RecipeDetailInfo> = MutableLiveData()

    val isLiked: MutableLiveData<Boolean> = MutableLiveData(false)
    val likeCount: MutableLiveData<Int> = MutableLiveData(0)

    val isFollowing: MutableLiveData<Boolean> = MutableLiveData(false)
    val followerCount: MutableLiveData<Int> = MutableLiveData(0)

    fun getRecipeInfoByID(recipeID: Int) {
        this.recipeID = recipeID
        sendRequest(
            request = { recipesAPIRepository.requestRecipeByID(recipeID) },
            success = { recipeDetailInfo ->
                recipeInfo.value = recipeDetailInfo!!
                isLiked.postValue(recipeDetailInfo.isLiked!!)
                isFollowing.postValue(recipeDetailInfo.user?.isFollowing!!)
                likeCount.postValue(recipeInfo.value!!.likeCount!!)
                followerCount.postValue(recipeInfo.value!!.user?.followedCount!!)
                getCommentsOfRecipe()
            },
            loadingVal = true
        )
    }

    private fun getCommentsOfRecipe() {

        sendRequest(
            request = { recipesAPIRepository.requestComments(recipeID) },
            success = { responseComments ->
                comments.value = responseComments?.data
                if (comments.value!!.size != 0) {
                    firstComment.value = responseComments!!.data[0]
                }
            },
            loadingVal = true

        )
    }

    fun likeRecipe() {

        if (isLiked.value == false) {
            sendRequest(
                request = { recipesAPIRepository.requestLikeRecipe(recipeID) },
                success = {
                    isLiked.postValue(true)
                    likeCount.postValue(likeCount.value?.plus(1))
                },
                error = { e ->
                    if (e is AuthException) {
                        e.id?.let { showLoginDialog(it) }
                    }
                },
            )
        } else {
            sendRequest(
                request = { recipesAPIRepository.requestDeleteLikeRecipe(recipeID) },
                success = {
                    isLiked.value = false
                    likeCount.postValue(likeCount.value?.minus(1))
                },
                error = { e ->
                    if (e is AuthException) {
                        e.id?.let { showLoginDialog(it) }
                    }
                },
            )
        }
    }

    fun followUser() {

        if (isFollowing.value == false) {
            sendRequest(
                request = { recipesAPIRepository.requestFollowUser(recipeInfo.value?.user?.id!!) },
                success = {
                    isFollowing.postValue(true)
                    followerCount.postValue(followerCount.value?.plus(1))
                },
                error = { e ->
                    if (e is AuthException) {
                        e.id?.let { showLoginDialog(it) }
                    }
                }

            )
        } else {
            sendRequest(
                request = { recipesAPIRepository.requestDeleteFollowUser(recipeInfo.value?.user?.id!!) },
                success = {
                    isFollowing.postValue(false)
                    followerCount.postValue(followerCount.value?.minus(1))
                },
                error = { e ->
                    if (e is AuthException) {
                        e.id?.let { showLoginDialog(it) }
                    }
                },
            )
        }
    }

    private fun showLoginDialog(@StringRes id: Int) {
        showAlertDialog(
            id,
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToLoginFragment()
        )
    }

    fun goToCommentPage() {
        navigate(
            RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToCommentFragment(
                comments.value!!, recipeID
            )
        )
    }
}
