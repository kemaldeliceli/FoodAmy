package com.lesson.foodamy.ui.recipe_categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.recipe_category.CategoryInfo
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCategoriesViewModel @Inject constructor(private val recipesAPIRepository: RecipesAPIRepository): BaseViewModel() {

    private val _responseRecipeCategory : MutableLiveData<ArrayList<CategoryInfo>> = MutableLiveData(
        arrayListOf())

    val responseRecipeCategory: LiveData<ArrayList<CategoryInfo>>
        get() = _responseRecipeCategory

    init {
        getRecipeCategories()
    }

    fun getRecipeCategories() = viewModelScope.launch {
        when(val response = recipesAPIRepository.requestRecipeCategories()){
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                _responseRecipeCategory.postValue(response.data.data)
            }
            null -> {}
        }
    }

    fun goToCategoryRecipes(position:Int){
        val categoryList = responseRecipeCategory.value
        val id = categoryList?.get(position)?.id!!
        val name = categoryList?.get(position)?.name!!

        navigate(MainFragmentDirections.
            actionMainFragmentToCategoryRecipesFragment(id,name))
    }

    fun goToRecipeDetail(recipeID : Int) {
        navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(recipeID))
    }
}