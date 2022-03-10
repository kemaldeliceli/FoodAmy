package com.lesson.foodamy.ui.recipedetail

import androidx.lifecycle.MutableLiveData
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.Comments
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getRecipeInfoByID(id: Int) {
        sendRequest(
            request = { recipesAPIRepository.requestRecipeByID(id) },
            success = { recipeDetailInfo ->
                recipeInfo.postValue(recipeDetailInfo)
                isLiked.postValue(recipeInfo.value!!.isLiked!!)
                likeCount.postValue(recipeInfo.value!!.likeCount!!)
                isFollowing.postValue(recipeInfo.value!!.user?.isFollowing!!)
                followedCount.postValue(recipeInfo.value!!.user?.followedCount!!)

                getCommentsOfRecipe(id)
            },
            loadingVal = true
        )
    }

    private fun getCommentsOfRecipe(id: Int) {
        sendRequest(
            request = { recipesAPIRepository.requestComments(id) },
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
                error = {
                    when (it) {
                        is AuthException -> {
                            showLoginDialog()
                        }
                        else -> it.message?.let { it1 -> showMessage(it1) }
                    }
                }
            )
        } else {
            sendRequest(
                request = { recipesAPIRepository.requestDeleteLikeRecipe(recipeID.value!!) },
                success = {
                    isLiked.value = false
                    likeCount.postValue(likeCount.value?.minus(1))
                },
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
                }
            )
        } else {
            sendRequest(
                request = { recipesAPIRepository.requestDeleteFollowUser(recipeInfo.value?.user?.id!!) },
                success = {
                    isFollowing.postValue(false)
                    followedCount.postValue(followedCount.value?.minus(1))
                }
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
