package com.lesson.foodamy.ui.recipedetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : BaseFragment<RecipeDetailViewModel,FragmentRecipeDetailBinding>(R.layout.fragment_recipe_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()

        arguments?.let {
            val args = RecipeDetailFragmentArgs.fromBundle(it)
            binding.recipeInfo = args.recipeInfo
        }

    }


    private fun setListeners() {
        binding.backIcon.setOnClickListener {
                findNavController().popBackStack()
        }
    }

    override fun getViewModelss(): Class<RecipeDetailViewModel> {
        return  RecipeDetailViewModel::class.java
    }


}