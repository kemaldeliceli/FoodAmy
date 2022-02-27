package com.lesson.foodamy.ui.recentlyadded

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.recipe_dataclass.Pagination
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentlyAddedViewModel @Inject constructor(private val recipeAPIRepository: RecipesAPIRepository ) : BaseViewModel(){

    var recipeList =  MutableLiveData<ArrayList<RecipeInfo>>()
    var pagination = MutableLiveData<Pagination>()
    init {
        getRecipesLastAdded(1)
    }


    fun getRecipesLastAdded(page: Int) = viewModelScope.launch {
        when(val response = recipeAPIRepository.requestRecipes(RecipeType.RECENTLY_LAST_ADDED,page)){
            is BaseResponse.Error -> {
                response.error.error?.let {
                    showMessage(it)
                }
            }
            is BaseResponse.Success -> {
                response.data?.let {
                    recipeList.postValue(it.data)
                    pagination.postValue(it.pagination!!)
                }

            }
            null -> {
                showMessage(R.string.null_error)
            }
        }

    }

    fun goToDetails(id: Int){
        navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(id))
    }
}