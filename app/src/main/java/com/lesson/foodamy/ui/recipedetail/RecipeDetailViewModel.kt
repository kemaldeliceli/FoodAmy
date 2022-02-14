package com.lesson.foodamy.ui.recipedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.repository.CommentApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val commentApiRepository: CommentApiRepository) : BaseViewModel(){

    var _responseMessage: MutableLiveData<BaseResponse<ResponseComments>> = MutableLiveData()


    val responseComments: LiveData<BaseResponse<ResponseComments>>
        get() = _responseMessage

    fun getCommentsOfRecipe(recipeID: Int) = viewModelScope.launch {
        _responseMessage.value = commentApiRepository.requestComments(recipeID)
    }
    fun getBack(){
        navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragment2ToMainFragment())
    }

}