package com.lesson.foodamy.ui.category_recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.repository.AuthAPIRepository
import com.lesson.foodamy.repository.RecipePagingSource
import com.lesson.foodamy.services.RecipeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryRecipesViewModel @Inject constructor(
    private val recipeService: RecipeService,
    private val authAPIRepository: AuthAPIRepository
) :
    BaseViewModel() {

    private val _responseCategoryRecipes: MutableLiveData<PagingData<RecipeInfo>> =
        MutableLiveData()

    val responseCategoryRecipes: LiveData<PagingData<RecipeInfo>>
        get() = _responseCategoryRecipes

    var categoryName: String = ""

    var categoryID = -1

    fun getListData() {
        sendRequest(
            request = {
                Pager(
                    config = PagingConfig(pageSize = 24, maxSize = 200),
                    pagingSourceFactory = {
                        RecipePagingSource(
                            recipeService,
                            RecipeType.CATEGORY_RECIPES_BY_ID,
                            categoryID
                        )
                    }
                ).flow
            },
            success = { pagingData ->
                pagingData.let {
                    viewModelScope.launch {
                        it.cachedIn(viewModelScope).collect {
                            _responseCategoryRecipes.postValue(it)
                        }
                    }
                }
            },
            loadingVal = true
        )
    }

    fun goToDetails(id: Int) {
        navigate(
            CategoryRecipesFragmentDirections
                .actionCategoryRecipesFragmentToRecipeDetailFragment2(id)
        )
    }

    fun goToLogin() {
        sendRequest(
            request = { authAPIRepository.requestLogout() },
            success = {
                navigate(
                    CategoryRecipesFragmentDirections.actionCategoryRecipesFragmentToLoginFragment()
                )
            },
            loadingVal = true
        )
    }
}
