package com.lesson.foodamy.ui.editorschoice

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentEditorsChoiceBinding
import com.lesson.foodamy.ui.main.NewRecipeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditorsChoiceFragment : BaseFragment<EditorsChoiceViewModel, FragmentEditorsChoiceBinding>
    (R.layout.fragment_editors_choice) {

    private var recipeAdapter: NewRecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCoordinateSnackbar(binding.coordinatorMessage)
        setupRecyclerView()
        setupObserves()
    }

    private fun setupObserves() {
        viewModel.recipeList.observe(viewLifecycleOwner) {
            recipeAdapter?.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        recipeAdapter = NewRecipeAdapter()
        recipeAdapter?.onClickListener={
            viewModel.goDetails(it)
        }
        binding.apply {
            recyclerViewRecipes.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewRecipes.adapter = recipeAdapter
        }
    }


    override fun getViewModelss(): Class<EditorsChoiceViewModel> {
        return EditorsChoiceViewModel::class.java
    }
}