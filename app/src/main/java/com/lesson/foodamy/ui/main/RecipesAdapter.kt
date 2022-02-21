package com.lesson.foodamy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeCardViewBinding
import com.lesson.foodamy.model.recipe_dataclass.Recipe
import com.lesson.foodamy.model.recipe_dataclass.RecipeInfo
import com.lesson.foodamy.model.recipe_dataclass.toResult

class RecipesAdapter(private val recipes: ArrayList<RecipeInfo>, val onClick: (position: Int, recipeInfo: RecipeInfo) -> Unit):
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    inner  class RecipesViewHolder(val binding: RecipeCardViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(foodInfo: Recipe){
            binding.recipeInfo = foodInfo

            itemView.setOnClickListener {
                onClick?.invoke(position,recipes[position])
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
    }