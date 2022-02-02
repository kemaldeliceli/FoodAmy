package com.lesson.foodamy.ui.recentlyadded

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.adapter.RecipesAdapter
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentRecentlyAddedBinding
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecentlyAddedFragment : BaseFragment<RecentlyAddedViewModel, FragmentRecentlyAddedBinding>(R.layout.fragment_recently_added) {

    lateinit var  recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCoordinateSnackbar(binding.snackbarCoord)
        viewModel.getRecipesLastAdded()
        getRecipesResponse()


        println("starttt")
    }

    private fun getRecipesResponse() {
        viewModel.responseRecipes.observe(viewLifecycleOwner,{response->
            when(response){
                is BaseResponse.Success -> {
                    val recipesAdapter = RecipesAdapter(response.data.data,this)
                    recyclerView = binding.recipesRecycleView
                    recyclerView.adapter = recipesAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this.context)

                }
                is BaseResponse.Error -> {
                    setSnackbar(response.error.error.toString())
                }
            }

            }
        )
    }


    override fun getViewModelss(): Class<RecentlyAddedViewModel> {
        return  RecentlyAddedViewModel::class.java
    }
}