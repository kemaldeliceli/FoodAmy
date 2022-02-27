package com.lesson.foodamy.ui.recipedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comments
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipesAPIRepository: RecipesAPIRepository,
    private val sharedPreferences: IPrefDefaultManager
    ) : BaseViewModel() {

    var recipeID: Int = -1

    var comments: MutableLiveData<Comments> = MutableLiveData()

    private val _responseComments: MutableLiveData<BaseResponse<ResponseComments>> =
        MutableLiveData()

    val responseComments: LiveData<BaseResponse<ResponseComments>>
        get() = _responseComments

    fun getCommentsOfRecipe() = viewModelScope.launch {
        val response = recipesAPIRepository.requestComments(recipeID)
        _responseComments.value = response!!

        when (response) {
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                comments.value = response.data.data
            }
            null -> {
            }
        }
    }

    private val _responseLike: MutableLiveData<BaseResponse<ResponseLike>> =
        MutableLiveData()

    val responseLike: LiveData<BaseResponse<ResponseLike>>
        get() = _responseLike

    val isLiked: MutableLiveData<Boolean> = MutableLiveData(false)





    fun goToCommentPage() {
        navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToCommentFragment(
            comments.value!!,recipeID))
    }

    fun likeRecipe()= viewModelScope.launch {
        val response = recipesAPIRepository.requestLikeRecipe(recipeID,sharedPreferences.getToken())
        _responseLike.value = response!!

        when (response) {
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                isLiked.postValue(true)
            }
            null -> {
            }
        }
    }


}