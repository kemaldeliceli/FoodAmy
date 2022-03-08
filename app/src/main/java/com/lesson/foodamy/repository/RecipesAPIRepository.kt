package com.lesson.foodamy.repository

import com.lesson.foodamy.model.ResponseComment
import com.lesson.foodamy.model.ResponseLike
import com.lesson.foodamy.model.comment_dataclass.Comment
import com.lesson.foodamy.model.comment_dataclass.ResponseComments
import com.lesson.foodamy.model.recipe_detail_info.RecipeDetailInfo
import com.lesson.foodamy.model.recipe_detail_info.ResponseFollow
import com.lesson.foodamy.services.RecipeService

class RecipesAPIRepository(
    private val recipeService: RecipeService,
    private val token: String,
) : BaseRepository() {

    suspend fun requestLikeRecipe(recipeID: Int): ResponseLike? =
        sendRequest { recipeService.likeRecipe(recipeID, this.token) }

    suspend fun requestDeleteLikeRecipe(recipeID: Int): ResponseLike? =
        sendRequest{recipeService.deleteLikeRecipe(recipeID, token)
        }
    suspend fun requestFollowUser(followedId: Int): ResponseFollow? =
        sendRequest{recipeService.followUser(followedId, token)}

    suspend fun requestDeleteFollowUser(followedId: Int): ResponseFollow? =
        sendRequest{recipeService.deleteFollowUser(followedId, token)}

    suspend fun requestComments(recipeID: Int): ResponseComments? =
        sendRequest{recipeService.getComments(recipeID, 1)}

    suspend fun requestAddComments(
        recipeID: Int,
        text: String,
    ): Comment? =
        sendRequest{recipeService.addComments(recipeID, token, text)}

    suspend fun requestDeleteComment(
        recipeID: Int,
        commentID: Int,
    ): ResponseComment? =
        sendRequest{recipeService.deleteComment(recipeID, commentID, token)}

    suspend fun requestEditComment(
        recipeID: Int,
        commentID: Int,
        text: String,
    ): ResponseComment? =
        sendRequest{recipeService.editComment(recipeID, commentID, text, token)}


    suspend fun requestRecipeByID(
        recipeID: Int,
    ): RecipeDetailInfo? = sendRequest{recipeService.getRecipeByID(recipeID, token)}

}
