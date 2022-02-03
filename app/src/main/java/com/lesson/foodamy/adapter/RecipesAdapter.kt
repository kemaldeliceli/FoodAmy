package com.lesson.foodamy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.databinding.RecipeCardViewBinding
import com.lesson.foodamy.model.recipe_dataclass.Data

class RecipesAdapter(private val recipes : ArrayList<Data>, private val context: Fragment):
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    inner  class RecipesViewHolder(val binding: RecipeCardViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(foodInfo: Data){
            binding.recipeInfo = foodInfo
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeCardViewBinding.inflate(inflater)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}