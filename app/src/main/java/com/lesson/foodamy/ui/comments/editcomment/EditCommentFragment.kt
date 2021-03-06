package com.lesson.foodamy.ui.comments.editcomment

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentEditCommentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCommentFragment : BaseFragment<EditCommentViewModel, FragmentEditCommentBinding>
(R.layout.fragment_edit_comment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = EditCommentFragmentArgs.fromBundle(it)
            viewModel.setComment(args.recipeID, args.comment)
        }
        setCoordinateSnackbar(binding.snackbarCoord)
    }

    override fun getViewModelss(): Class<EditCommentViewModel> {
        return EditCommentViewModel::class.java
    }
}
