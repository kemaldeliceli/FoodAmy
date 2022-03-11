package com.lesson.foodamy.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lesson.foodamy.model.RecipeType
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.services.RecipeService

class RecipePagingSource constructor(
    private val recipeService: RecipeService,
    private val recipeType: RecipeType,
    private val categoryID: Int?

) :
    PagingSource<Int, RecipeInfo>() {
    override fun getRefreshKey(state: PagingState<Int, RecipeInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeInfo> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

            val response = when (recipeType) {
                RecipeType.EDITORS_CHOICE -> recipeService.getEditorsChoice(nextPage)
                RecipeType.RECENTLY_LAST_ADDED -> {
                    println("recently_added")
                    recipeService.getLastAdded(nextPage)
                }
                RecipeType.CATEGORY_RECIPES_BY_ID -> {
                    println("category_id $categoryID")
                    recipeService.getCategoryRecipes(categoryID!!)
                }
            }

            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null

            response.pagination?.let {
                if (it.currentPage!! < it.lastPage!!) {
                    nextPageNumber = it.currentPage + 1
                }
                if (it.currentPage> 1) {
                    prevPageNumber = it.currentPage - 1
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
