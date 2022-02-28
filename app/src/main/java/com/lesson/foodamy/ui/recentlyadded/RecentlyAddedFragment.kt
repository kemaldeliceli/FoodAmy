package com.lesson.foodamy.ui.recentlyadded

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentRecentlyAddedBinding
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.model.recipe_dataclass.Pagination
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.ui.main.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecentlyAddedFragment : BaseFragment<RecentlyAddedViewModel, FragmentRecentlyAddedBinding>(R.layout.fragment_recently_added) {

    private var recipesAdapter: RecipeAdapter?=null

    private var layoutManager: LinearLayoutManager?=null
    private var listSize = 0
    private var pagination: Pagination?=null

    val recipeList = ArrayList<RecipeInfo>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())

        setCoordinateSnackbar(binding.snackbarCoord)
        setupRecycleView()
        setObserver()
        setOnScrollListener()
    }

    private fun setOnScrollListener() {
        binding.recipesRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (layoutManager?.findLastCompletelyVisibleItemPosition() == listSize-1 &&
                    pagination?.currentPage!! < pagination?.lastPage!!){
                        binding.progressBar.visibility = View.VISIBLE
                        viewModel.getRecipesLastAdded(pagination?.currentPage!!+1)
                }
            }
        }

        )
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

    private fun setObserver() {
       viewModel.recipeList.observe(viewLifecycleOwner,{
               updateData(it)
               listSize = it.size
               binding.progressBar.visibility = View.GONE

       })
       viewModel.pagination.observe(viewLifecycleOwner,{
           pagination = it
       })
    }
    private fun updateData(newData: ArrayList<RecipeInfo>){
        recipeList.addAll(newData) // add new data to existing data
        recipesAdapter?.submitList(recipeList) //submit the data again
    }

    override fun getViewModelss(): Class<RecentlyAddedViewModel> {
        return  RecentlyAddedViewModel::class.java
    }

}