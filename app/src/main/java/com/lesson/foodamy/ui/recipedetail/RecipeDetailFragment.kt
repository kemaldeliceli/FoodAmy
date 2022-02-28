package com.lesson.foodamy.ui.recipedetail

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentRecipeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : BaseFragment<RecipeDetailViewModel,FragmentRecipeDetailBinding>(R.layout.fragment_recipe_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.recipeID.value = RecipeDetailFragmentArgs.fromBundle(it).recipeID

        }
        viewModel.getRecipeInfoByID()
        viewModel.getCommentsOfRecipe()

        setCoordinateSnackbar(binding.snackbarCoord)
    }

    override fun getViewModelss(): Class<RecipeDetailViewModel> {
        return  RecipeDetailViewModel::class.java
    }


}