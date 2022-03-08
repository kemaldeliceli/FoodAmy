package com.lesson.foodamy.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeDetailCommentCardviewBinding
import com.lesson.foodamy.model.comment_dataclass.Comment

class CommentAdapter() : PagingDataAdapter<Comment, CommentAdapter.CommentViewHolder>(
    object : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem == newItem
    }

) {
    var deleteClickListener: ((id: Int) -> Unit)? = null
    var editClickListener: ((comment: Comment) -> Unit)? = null
    var userID: Int? = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CommentAdapter.CommentViewHolder {
        val binding = RecipeDetailCommentCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CommentAdapter.CommentViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position)!!)
    }

    inner class CommentViewHolder(val binding: RecipeDetailCommentCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment = comment
            binding.currentUserID = userID
            binding.imageButtonDeleteComment.setOnClickListener {
                deleteClickListener?.invoke(getItem(position)!!.id!!)
            }
            binding.imageButtonEditComment.setOnClickListener {
                editClickListener?.invoke(getItem(position)!!)
            }
        }
    }
}
