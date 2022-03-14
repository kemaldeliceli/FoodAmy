package com.lesson.foodamy.ui.recipe_categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.MiniRecipeCardviewBinding
import com.lesson.foodamy.model.recipe_category.Recipes

class RecipeImagesAdapter(private val recipes: ArrayList<Recipes>/*, val onClick: ((position: Int) -> Unit)?*/) :
    RecyclerView.Adapter<RecipeImagesAdapter.RecipeImagesViewHolder>() {

    var onClickListener: ((id: Int) -> Unit)? = null

    inner class RecipeImagesViewHolder(val binding: MiniRecipeCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipes) {
            binding.recipe = recipe
            binding.root.setOnClickListener {
                onClickListener?.invoke(recipe.id!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeImagesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = MiniRecipeCardviewBinding.inflate(inflater)
        return RecipeImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeImagesViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
