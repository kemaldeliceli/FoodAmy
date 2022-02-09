package com.lesson.foodamy.ui.recentlyadded

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.adapter.RecipesAdapter
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentRecentlyAddedBinding
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.ui.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecentlyAddedFragment : BaseFragment<RecentlyAddedViewModel, FragmentRecentlyAddedBinding>(R.layout.fragment_recently_added) {

    lateinit var recipeList: ArrayList<RecipeInfo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCoordinateSnackbar(binding.snackbarCoord)
        viewModel.getRecipesLastAdded()
        getRecipesResponse()
    }

    private fun getRecipesResponse() {
        viewModel.responseRecipes.observe(viewLifecycleOwner,{response->
            when(response){
                is BaseResponse.Success -> {
                    recipeList = response.data.data
                    val recipesAdapter = RecipesAdapter(recipeList, { position -> onClick(position)  })
                    recipesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    binding.recipesRecycleView.adapter = recipesAdapter
                    binding.recipesRecycleView.layoutManager = LinearLayoutManager(this.context)

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

    fun onClick(position: Int){
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(recipeList[position]))
    }

}