package com.lesson.foodamy.ui.editorschoice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorsChoiceViewModel @Inject
constructor(private val recipeAPIRepository: RecipesAPIRepository) : BaseViewModel() {

    val recipeList = MutableLiveData<List<RecipeInfo>>()
    var nextPage : Int = 1
    init {
        getRecipesEditorsChoice()
    }

    private fun getRecipesEditorsChoice() = viewModelScope.launch {
        when (val response = recipeAPIRepository.requestRecipes(RecipeType.EDITORS_CHOICE,nextPage)) {
            is BaseResponse.Success -> {
                response.data.data.let {
                    recipeList.postValue(it)
                }
            }

            is BaseResponse.Error -> {
                response.error.error?.let { showMessage(it) }
            }

            null -> {
                showMessage(R.string.null_error)
            }
        }

    }

    fun goDetails(id :Int) {
        navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(id))
    }

}