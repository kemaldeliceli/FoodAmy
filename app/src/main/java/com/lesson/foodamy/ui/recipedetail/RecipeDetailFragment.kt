package com.lesson.foodamy.ui.recipedetail

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentRecipeDetailBinding
import com.lesson.foodamy.model.BaseResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment :
    BaseFragment<RecipeDetailViewModel, FragmentRecipeDetailBinding>(R.layout.fragment_recipe_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = RecipeDetailFragmentArgs.fromBundle(it)
            binding.recipeInfo = args.recipeInfo
            viewModel.getCommentsOfRecipe(recipeID = args.recipeInfo.id!!)
            println(args.recipeInfo.id)
        }

        binding.viewModel=viewModel
        setListeners()
    }

    private fun setListeners() {
        viewModel.responseComments.observe(viewLifecycleOwner) { response ->
            when (response) {
                is BaseResponse.Error -> {
                    println(response.error.error.toString())
                }
                is BaseResponse.Success -> {
                    val comments = response.data.data
                    if (comments.isNotEmpty()) {
                        binding.comment = comments[0]
                    }
                }
            }


        }
    }


    override fun getViewModelss(): Class<RecipeDetailViewModel> {
        return RecipeDetailViewModel::class.java
    }


}