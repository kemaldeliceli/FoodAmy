package com.lesson.foodamy.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.services.RecipeService

class CommentPagingSource constructor(
    private val recipeService: RecipeService,
    private val recipeID: Int?
) :
    PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

            val response = recipeService.getComments(recipeID!!, nextPage)

            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null

            response.pagination.let {
                if (it.current_page < it.last_page) {
                    nextPageNumber = it.current_page + 1
                }
                if (it.current_page> 1) {
                    prevPageNumber = it.current_page - 1
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
