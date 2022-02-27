package com.lesson.foodamy.ui.recipe_categories

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentRecipeCategoriesBinding
import com.lesson.foodamy.model.recipe_category.CategoryInfo
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeCategoriesFragment :
    BaseFragment<RecipeCategoriesViewModel, FragmentRecipeCategoriesBinding>(
        R.layout.fragment_recipe_categories) {

    lateinit var categoryRecipesList: ArrayList<CategoryInfo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        viewModel.getRecipeCategories()
    }

    private fun setListeners() {
        viewModel.responseRecipeCategory.observe(viewLifecycleOwner, { categoryList ->
            if (categoryList.isNotEmpty()) {
                categoryRecipesList = categoryList
                binding.categoryRecycleView.adapter =
                    RecipeCategoriesAdapter(categoryList, { position -> onClick(position) })
                binding.categoryRecycleView.layoutManager = LinearLayoutManager(this.context)
            }
        }
        )
    }

    fun onClick(position: Int) {
        navigate(MainFragmentDirections.actionMainFragmentToCategoryRecipesFragment(
            categoryRecipesList[position].id!!,categoryRecipesList[position].name!!))
    }

    override fun getViewModelss(): Class<RecipeCategoriesViewModel> {
        return RecipeCategoriesViewModel::class.java
    }
}