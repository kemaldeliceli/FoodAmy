package com.lesson.foodamy.ui.category_recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseViewModel
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.repository.AuthApiRepository
import com.lesson.foodamy.repository.RecipePagingSource
import com.lesson.foodamy.repository.RecipesAPIRepository
import com.lesson.foodamy.services.RecipeService
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryRecipesViewModel @Inject constructor(
    private val recipeService: RecipeService,
    private val authApiRepository: AuthApiRepository,
    private val loginSharedPreferences: IPrefDefaultManager
    ) :
    BaseViewModel() {

    private val _responseCategoryRecipes: MutableLiveData<PagingData<RecipeInfo>> =
        MutableLiveData()

    val responseCategoryRecipes: LiveData<PagingData<RecipeInfo>>
        get() = _responseCategoryRecipes

    var categoryName: String = ""

    var categoryID = -1


    fun getListData() {
         Pager(config = PagingConfig(pageSize = 24, maxSize = 200),
            pagingSourceFactory = {
                RecipePagingSource(
                    recipeService,
                    RecipeType.CATEGORY_RECIPES_BY_ID,
                    categoryID
                )
            }).flow.let {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        _responseCategoryRecipes.postValue(it)
                    }
                }
            }


    }

    fun goToDetails(id: Int) {
        navigate(CategoryRecipesFragmentDirections.
        actionCategoryRecipesFragmentToRecipeDetailFragment2(id))
    }

    fun goToLogin() = viewModelScope.launch{
        when(val response = authApiRepository.requestLogout()){
            is BaseResponse.Success->{
                showMessage(R.string.successfull_logout)
                loginSharedPreferences.saveLogin(isLogged = false)
                loginSharedPreferences.setToken("")
                loginSharedPreferences.setUserInfo(null)
                navigate(CategoryRecipesFragmentDirections.actionCategoryRecipesFragmentToLoginFragment())
            }
            is BaseResponse.Error->{
                showMessage(response.error.error.toString())
            }
        }

    }
    }