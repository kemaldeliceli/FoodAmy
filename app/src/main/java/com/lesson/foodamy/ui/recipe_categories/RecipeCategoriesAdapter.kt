package com.lesson.foodamy.ui.recipe_categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeCategoryCardviewBinding
import com.lesson.foodamy.model.recipe_category.CategoryInfo

class RecipeCategoriesAdapter (private val categories : ArrayList<CategoryInfo>, val onClick: ((position: Int) -> Unit)?):
    RecyclerView.Adapter<RecipeCategoriesAdapter.CategoryViewHolder>() {

    inner  class CategoryViewHolder(val binding: RecipeCategoryCardviewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category: CategoryInfo){
            binding.recipeCategory = category

            binding.imageRecycleView.adapter = RecipeImagesAdapter(category.recipes)

            binding.seeAllText.setOnClickListener {
                onClick?.invoke(position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeCategoryCardviewBinding.inflate(inflater)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}