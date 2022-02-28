package com.lesson.foodamy.ui.recipe_categories

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentRecipeCategoriesBinding
import com.lesson.foodamy.model.recipe_category.CategoryInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeCategoriesFragment :
    BaseFragment<RecipeCategoriesViewModel, FragmentRecipeCategoriesBinding>(
        R.layout.fragment_recipe_categories) {

    private var recipeCategoriesAdapter: RecipeCategoriesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setObservers()
    }

    private fun setupRecyclerView() {
        recipeCategoriesAdapter = RecipeCategoriesAdapter()
        recipeCategoriesAdapter?.onClickListener = {
            viewModel.goToCategoryRecipes(it)
        }
        recipeCategoriesAdapter?.recipeImageClickListener = {
            viewModel.goToRecipeDetail(it)
        }
        binding.apply {
            categoryRecycleView.layoutManager = LinearLayoutManager(requireContext())
            categoryRecycleView.adapter = recipeCategoriesAdapter
        }
    }


    private fun setObservers() {
        viewModel.responseRecipeCategory.observe(viewLifecycleOwner, { categoryList ->
            if (categoryList.isNotEmpty()) {
                recipeCategoriesAdapter?.submitList(categoryList)
            }})
    }

    override fun getViewModelss(): Class<RecipeCategoriesViewModel> {
        return RecipeCategoriesViewModel::class.java
    }
}