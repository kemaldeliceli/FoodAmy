package com.lesson.foodamy.ui.recentlyadded

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentRecentlyAddedBinding
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.ui.main.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class RecentlyAddedFragment : BaseFragment<RecentlyAddedViewModel, FragmentRecentlyAddedBinding>(R.layout.fragment_recently_added) {

    private var recipesAdapter: RecipeAdapter?=null

    private var layoutManager: LinearLayoutManager?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())

        setCoordinateSnackbar(binding.snackbarCoord)
        setupRecycleView()
        submitLastData()
    }


    private fun setupRecycleView() {
        recipesAdapter = RecipeAdapter()
        recipesAdapter?.onClickListener = {
            viewModel.goToDetails(it)
        }
        binding.apply {
            recipesRecycleView.layoutManager = layoutManager
            recipesRecycleView.adapter = recipesAdapter
        }
    }

    private fun submitLastData(){
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recipesAdapter?.submitData(it)
            }
        }
    }


    override fun getViewModelss(): Class<RecentlyAddedViewModel> {
        return  RecentlyAddedViewModel::class.java
    }

}