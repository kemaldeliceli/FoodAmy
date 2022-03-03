package com.lesson.foodamy.ui.recipe_categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.recipe_category.CategoryInfo
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.repository.CategoryPagingSource
import com.lesson.foodamy.repository.RecipePagingSource
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.services.RecipeService
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCategoriesViewModel @Inject constructor(private val recipeService: RecipeService): BaseViewModel() {

    private val _responseRecipeCategory : MutableLiveData<PagingData<CategoryInfo>> = MutableLiveData()

    val responseRecipeCategory: LiveData<PagingData<CategoryInfo>>
        get() = _responseRecipeCategory


    fun getListData(){
        Pager (config = PagingConfig(pageSize = 4, maxSize = 200),
            pagingSourceFactory = {
                CategoryPagingSource(
                    recipeService
                )
            }).flow.let {
            viewModelScope.launch {
                it.cachedIn(viewModelScope).collect {
                    _responseRecipeCategory.postValue(it)
                }
            }
        }
    }

    /*fun getRecipeCategories() = viewModelScope.launch {
        when(val response = recipesAPIRepository.requestRecipeCategories()){
            is BaseResponse.Error -> {
                showMessage(response.error.error.toString())
            }
            is BaseResponse.Success -> {
                _responseRecipeCategory.postValue(response.data.data)
            }
            null -> {}
        }
    }*/

    fun goToCategoryRecipes(id:Int,name:String){

        navigate(MainFragmentDirections.
            actionMainFragmentToCategoryRecipesFragment(id,name))
    }

    fun goToRecipeDetail(recipeID : Int) {
        navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(recipeID))
    }
}