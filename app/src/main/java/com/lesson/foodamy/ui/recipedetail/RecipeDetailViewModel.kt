package com.lesson.foodamy.ui.recipedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.repository.CommentApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor
    (private val commentApiRepository: CommentApiRepository) : BaseViewModel() {

    private var _responseMessage: MutableLiveData<BaseResponse<ResponseComments>> =
        MutableLiveData()

    val testText = MutableLiveData("INIT")

    val responseComments: LiveData<BaseResponse<ResponseComments>>
        get() = _responseMessage

    fun getCommentsOfRecipe(recipeID: Int) = viewModelScope.launch {
        when (val response = commentApiRepository.requestComments(recipeID)) {
            is BaseResponse.Error -> {
                testText.postValue("ERROR")
            }
            is BaseResponse.Success -> {
                _responseMessage.value = response
                testText.postValue("succes")
                testText.postValue(response.data.data.firstOrNull()?.text ?: "NULL")
            }
            null -> TODO()
        }
    }

    fun getBack() {
        popBackStack()
    }


    fun addComment() {
        navigate(
            RecipeDetailFragmentDirections
                .actionRecipeDetailFragment2ToRecentlyAddedFragment()
        )
    }

}