package com.lesson.foodamy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeCardViewBinding
import com.lesson.foodamy.model.recipe_dataclass.Recipe
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.model.recipe_dataclass.toResult


class RecipeAdapter() : ListAdapter<RecipeInfo, RecipeAdapter.RecipesViewHolder>(
    object : DiffUtil.ItemCallback<RecipeInfo>() {
        override fun areItemsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo) =
            oldItem == newItem

    }
) {

    var onClickListener: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding =
            RecipeCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return RecipesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(getItem(position).toResult(), position)
    }

    inner class RecipesViewHolder(val binding: RecipeCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodInfo: Recipe, position: Int) {
            binding.recipeInfo = foodInfo
            binding.root.setOnClickListener {
                onClickListener?.invoke(getItem(position).id!!)
            }
        }
    }
}