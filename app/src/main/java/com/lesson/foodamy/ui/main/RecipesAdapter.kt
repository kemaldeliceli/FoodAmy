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

class RecipesAdapter(
    private val recipes: ArrayList<RecipeInfo>,
    val onClick: (position: Int, recipeInfo: RecipeInfo) -> Unit
) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    inner class RecipesViewHolder(val binding: RecipeCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodInfo: Recipe) {
            binding.recipeInfo = foodInfo

            itemView.setOnClickListener {
                onClick.invoke(absoluteAdapterPosition, recipes[absoluteAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeCardViewBinding.inflate(inflater)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes[position].toResult())
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setList(it: List<RecipeInfo>) {
        recipes.addAll(it)
    }
}

class NewRecipeAdapter() : ListAdapter<RecipeInfo, NewRecipeAdapter.RecipesViewHolder>(
    object : DiffUtil.ItemCallback<RecipeInfo>() {
        override fun areItemsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo) =
            oldItem == newItem

    }
) {

    var onClickListener: ((data: RecipeInfo) -> Unit)? = null

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
                onClickListener?.invoke(getItem(position))
            }
        }
    }
}