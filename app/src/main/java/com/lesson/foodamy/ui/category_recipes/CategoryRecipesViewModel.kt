package com.lesson.foodamy.ui.category_recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.repository.RecipesAPIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryRecipesViewModel @Inject constructor(private val recipesAPIRepository: RecipesAPIRepository) :
    BaseViewModel() {

    private val _responseCategoryRecipes: MutableLiveData<ArrayList<RecipeInfo>> = MutableLiveData()

    val responseCategoryRecipes: LiveData<ArrayList<RecipeInfo>>
        get() = _responseCategoryRecipes

    fun getCategoryRecipes(recipeID: Int) = viewModelScope.launch {
        val response = recipesAPIRepository.requestCategoryRecipes(recipeID = recipeID)
        when (response) {
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                _responseCategoryRecipes.value = response.data.data
            }
            null -> {
            }
        }
    }
}