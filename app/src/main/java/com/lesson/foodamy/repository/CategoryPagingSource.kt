package com.lesson.foodamy.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lesson.foodamy.model.recipe_category.CategoryInfo
import com.lesson.foodamy.services.RecipeService

class CategoryPagingSource constructor(private val recipeService: RecipeService) :
    PagingSource<Int, CategoryInfo>() {
    override fun getRefreshKey(state: PagingState<Int, CategoryInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoryInfo> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

            val response = recipeService.getRecipeCategories(nextPage)

            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null

            response.pagination?.let {
                if (it.currentPage!! < it.lastPage!!) {
                    nextPageNumber = it.currentPage!! + 1
                }
                if (it.currentPage!! > 1) {
                    prevPageNumber = it.currentPage!! - 1
                }
            }

            LoadResult.Page(
                data = response.data,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}
