package com.lesson.foodamy.ui.category_recipes


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.CategoryRecipesFragmentBinding
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.ui.main.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryRecipesFragment : BaseFragment<CategoryRecipesViewModel,CategoryRecipesFragmentBinding>(
    R.layout.category_recipes_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            val args = CategoryRecipesFragmentArgs.fromBundle(it)
            val recipeID = args.recipeID
            viewModel.getCategoryRecipes(recipeID)
        }
        setCoordinateSnackbar(binding.snackbarCoord)
        setObserver()
    }

    private fun setObserver() {
        viewModel.responseCategoryRecipes.observe(viewLifecycleOwner,{ recipes->
                binding.categoryRecipesRecycleview.adapter = RecipesAdapter(recipes
                ) { position, recipeInfo -> onClick(position, recipeInfo) }
            binding.categoryRecipesRecycleview.layoutManager =  LinearLayoutManager(this.context)
            }
        )
    }

    fun onClick(position: Int, recipeInfo:RecipeInfo){
        navigate(CategoryRecipesFragmentDirections.actionCategoryRecipesFragmentToRecipeDetailFragment2(recipeInfo))
    }
    override fun getViewModelss(): Class<CategoryRecipesViewModel> {
        return CategoryRecipesViewModel::class.java
    }
}