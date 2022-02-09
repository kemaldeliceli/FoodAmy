package com.lesson.foodamy.ui.editorschoice

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.adapter.RecipesAdapter
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentEditorsChoiceBinding
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.ui.MainFragment
import com.lesson.foodamy.ui.MainFragmentDirections
import com.lesson.foodamy.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition


@AndroidEntryPoint
class EditorsChoiceFragment : BaseFragment<EditorsChoiceViewModel, FragmentEditorsChoiceBinding>(R.layout.fragment_editors_choice){

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
                    val recipesAdapter = RecipesAdapter(recipeList, {position ->  onClick(position) } )
                    recipesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    binding.recipesRecycleView.adapter = recipesAdapter
                    binding.recipesRecycleView.layoutManager =  LinearLayoutManager(this.context)

                }
                is BaseResponse.Error -> {
                    setSnackbar(response.error.error.toString())
                }
            }

        }
        )
    }
    fun onClick(position: Int){
       findNavController().navigate(MainFragmentDirections.actionMainFragmentToRecipeDetailFragment2(recipeList[position]))
    }

    override fun getViewModelss(): Class<EditorsChoiceViewModel> {
        return  EditorsChoiceViewModel::class.java
    }


}