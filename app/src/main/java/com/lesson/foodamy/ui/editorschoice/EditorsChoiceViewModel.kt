package com.lesson.foodamy.ui.editorschoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.model.recipe_dataclass.ResponseRecipes
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.RecipeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorsChoiceViewModel @Inject constructor(private val recipeAPIRepository: RecipesAPIRepository ) : BaseViewModel(){

    private var _responseMessage: MutableLiveData<BaseResponse<ResponseRecipes>> = MutableLiveData()

    val responseRecipes: LiveData<BaseResponse<ResponseRecipes>>
        get() = _responseMessage

    fun getRecipesLastAdded() = viewModelScope.launch {
        _responseMessage.value = recipeAPIRepository.requestRecipes(RecipeType.EDITORS_CHOICE)
    }

}