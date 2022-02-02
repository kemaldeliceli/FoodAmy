package com.lesson.foodamy.ui.editorschoice

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.adapter.RecipesAdapter
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentEditorsChoiceBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditorsChoiceFragment : BaseFragment<EditorsChoiceViewModel, FragmentEditorsChoiceBinding>(R.layout.fragment_editors_choice) {

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


    override fun getViewModelss(): Class<EditorsChoiceViewModel> {
        return  EditorsChoiceViewModel::class.java
    }
}