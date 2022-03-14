package com.lesson.foodamy.ui.comments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentCommentBinding
import com.lesson.foodamy.preferences.IPrefDefaultManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommentFragment :
    BaseFragment<CommentViewModel, FragmentCommentBinding>
    (R.layout.fragment_comment) {

    private var commentAdapter: CommentAdapter? = null

    @Inject
    lateinit var sharedPreferences: IPrefDefaultManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = CommentFragmentArgs.fromBundle(it)
            viewModel.setRecipeId(args.recipeID)
        }
        setupRecycleView()
        setCoordinateSnackbar(binding.snackbarCoord)
        submitLastData()
    }

    private fun submitLastData() {
        viewModel.comments.observe(viewLifecycleOwner) {
            commentAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupRecycleView() {
        commentAdapter = CommentAdapter()
        commentAdapter!!.userID =
            if (sharedPreferences.getUserInfo() == null) -1 else sharedPreferences.getUserInfo()!!.id!!
        commentAdapter?.deleteClickListener = {
            viewModel.deleteComment(it)
        }
        commentAdapter?.editClickListener = {
            viewModel.navigateToEditComment(it)
        }
        binding.apply {
            commentRecycleView.layoutManager = LinearLayoutManager(requireContext())
            commentRecycleView.adapter = commentAdapter
        }
    }

    override fun getViewModelss(): Class<CommentViewModel> {
        return CommentViewModel::class.java
    }
}
