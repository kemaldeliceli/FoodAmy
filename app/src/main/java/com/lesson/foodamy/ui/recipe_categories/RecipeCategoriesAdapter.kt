package com.lesson.foodamy.ui.recipe_categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeCategoryCardviewBinding
import com.lesson.foodamy.model.recipe_category.CategoryInfo

class RecipeCategoriesAdapter : PagingDataAdapter<CategoryInfo, RecipeCategoriesAdapter
    .RecipeCategoriesViewHolder>(
    object : DiffUtil.ItemCallback<CategoryInfo>() {
        override fun areItemsTheSame(oldItem: CategoryInfo, newItem: CategoryInfo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoryInfo, newItem: CategoryInfo): Boolean =
            oldItem == newItem
    }

) {
    var onClickListener: ((id: Int, name: String) -> Unit)? = null
    var recipeImageClickListener: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCategoriesAdapter
    .RecipeCategoriesViewHolder {
        val binding =
            RecipeCategoryCardviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return RecipeCategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecipeCategoriesAdapter
        .RecipeCategoriesViewHolder,
        position: Int,
    ) {
        holder.bind(position, getItem(position)!!)
    }

    inner class RecipeCategoriesViewHolder(val binding: RecipeCategoryCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, category: CategoryInfo) {

            binding.recipeCategory = category

            val recipeImagesAdapter = RecipeImagesAdapter(category.recipes)
            recipeImagesAdapter.onClickListener = recipeImageClickListener

            binding.imageRecycleView.adapter = recipeImagesAdapter

            binding.seeAllText.setOnClickListener {
                onClickListener?.invoke(getItem(position)?.id!!, getItem(position)?.name!!)
            }
        }
    }
}
