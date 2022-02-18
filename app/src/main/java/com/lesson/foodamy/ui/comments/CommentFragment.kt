package com.lesson.foodamy.ui.comments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentCommentBinding
import com.lesson.foodamy.model.BaseResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : BaseFragment<CommentViewModel,FragmentCommentBinding>(R.layout.fragment_comment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = CommentFragmentArgs.fromBundle(it)
            val commentList = args.comments
            viewModel.recipeID = args.recipeID
            binding.commentRecycleView.adapter = CommentsAdapter(comments = commentList)
            binding.commentRecycleView.layoutManager =  LinearLayoutManager(this.context)

        }
        setCoordinateSnackbar(binding.snackbarCoord)
        setListeners()
    }

    private fun setListeners() {
        viewModel.responseComments.observe(viewLifecycleOwner,{response->
            when(response){
                is BaseResponse.Error -> {
                    println(response.error.error.toString())
                }
                is BaseResponse.Success -> {
                    val comments = response.data.data
                    if (comments.isNotEmpty()) {
                        binding.commentRecycleView.adapter = CommentsAdapter(comments = comments)
                        binding.commentRecycleView.layoutManager =  LinearLayoutManager(this.context)
                    }
                }
            }
        })

    }

    override fun getViewModelss(): Class<CommentViewModel> {
        return CommentViewModel::class.java
    }

}