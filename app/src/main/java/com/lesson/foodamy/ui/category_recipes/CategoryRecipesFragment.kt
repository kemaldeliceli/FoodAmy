package com.lesson.foodamy.ui.category_recipes


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.CategoryRecipesFragmentBinding
import com.lesson.foodamy.ui.main.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryRecipesFragment :
    BaseFragment<CategoryRecipesViewModel, CategoryRecipesFragmentBinding>(
        R.layout.category_recipes_fragment
    ) {

    private var recipeAdapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            val args = CategoryRecipesFragmentArgs.fromBundle(it)
            val recipeID = args.categoryID
            viewModel.categoryName.postValue(args.categoryName)
            viewModel.getCategoryRecipes(recipeID)
        }
        setCoordinateSnackbar(binding.snackbarCoord)
        setupRecyclerView()
        setObserver()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter()
        recipeAdapter?.onClickListener = {
            viewModel.goToDetails(it)
        }
        binding.apply {
            categoryRecipesRecycleview.layoutManager = LinearLayoutManager(requireContext())
            categoryRecipesRecycleview.adapter = recipeAdapter
        }
    }

    private fun setObserver() {
        viewModel.responseCategoryRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter?.submitList(recipes)
        }
    }


    override fun getViewModelss(): Class<CategoryRecipesViewModel> {
        return CategoryRecipesViewModel::class.java
    }
}