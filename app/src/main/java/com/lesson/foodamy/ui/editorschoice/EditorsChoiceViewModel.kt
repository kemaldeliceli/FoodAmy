package com.lesson.foodamy.ui.editorschoice

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
import com.lesson.foodamy.repository.RecipePagingSource
import com.lesson.foodamy.services.RecipeService
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorsChoiceViewModel @Inject
constructor(
    private val recipeService: RecipeService,
) : BaseViewModel() {

    private val _responseEditorsChoice: MutableLiveData<PagingData<RecipeInfo>> = MutableLiveData()

    val responseEditorsChoice: LiveData<PagingData<RecipeInfo>>
        get() = _responseEditorsChoice

    fun getListData() = viewModelScope.launch {
        sendRequest(
            request = {
                Pager(
                    config = PagingConfig(pageSize = 24, maxSize = 200),
                    pagingSourceFactory = {
                        RecipePagingSource(
                            recipeService,
                            RecipeType.EDITORS_CHOICE,
                            null
                        )
                    }
                ).flow
            },
            success = { pagingData ->
                pagingData.let {
                    viewModelScope.launch {
                        it.cachedIn(viewModelScope).collect {
                            _responseEditorsChoice.postValue(it)
                        }
                    }
                }
            },
            loadingVal = true
        )
    }

    fun goDetails(id: Int) {
        navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(id))
    }
}
