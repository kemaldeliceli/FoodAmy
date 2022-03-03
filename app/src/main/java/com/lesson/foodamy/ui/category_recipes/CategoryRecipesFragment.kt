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
            viewModel.categoryID = args.categoryID
            viewModel.categoryName= args.categoryName
            viewModel.getListData()
        }
        setCoordinateSnackbar(binding.snackbarCoord)
        setupRecyclerView()
        submitLastData()
    }

    private fun submitLastData(){

        viewModel.responseCategoryRecipes.observe(viewLifecycleOwner) {
            recipeAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
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
    override fun getViewModelss(): Class<CategoryRecipesViewModel> {
        return CategoryRecipesViewModel::class.java
    }
}