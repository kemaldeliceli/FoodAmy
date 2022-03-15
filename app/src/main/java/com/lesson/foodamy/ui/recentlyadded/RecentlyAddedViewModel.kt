package com.lesson.foodamy.ui.recentlyadded

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
class RecentlyAddedViewModel @Inject constructor(
    private val recipeService: RecipeService,
) : BaseViewModel() {

    private val _responseRecentlyAdded: MutableLiveData<PagingData<RecipeInfo>> = MutableLiveData()

    val responseRecentlyAdded: LiveData<PagingData<RecipeInfo>>
        get() = _responseRecentlyAdded

    fun getListData() {
        sendRequest(
            request = {
                Pager(
                    config = PagingConfig(pageSize = 24, maxSize = 72),
                    pagingSourceFactory = {
                        RecipePagingSource(
                            recipeService,
                            RecipeType.RECENTLY_LAST_ADDED,
                            null

                        )
                    }
                ).flow
            },
            success = { pagingData ->
                pagingData.let {
                    viewModelScope.launch {
                        it.cachedIn(viewModelScope).collect {
                            _responseRecentlyAdded.postValue(it)
                        }
                    }
                }
            },
            loadingVal = true
        )
    }

    fun goToDetails(id: Int) {
        navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(id))
    }
}
