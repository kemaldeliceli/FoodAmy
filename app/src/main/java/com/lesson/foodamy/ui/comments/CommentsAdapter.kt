package com.lesson.foodamy.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeDetailCommentCardviewBinding
import com.lesson.foodamy.model.comment_dataclass.Comment

class CommentsAdapter(private val comments: List<Comment>) :
RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>(){
    inner class CommentsViewHolder(val binding: RecipeDetailCommentCardviewBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(comment: Comment){
            binding.comment = comment

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeDetailCommentCardviewBinding.inflate(inflater)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}