package com.lesson.foodamy.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lesson.foodamy.ui.home.HomeFragment
import com.lesson.foodamy.ui.recipe_categories.RecipeCategoriesFragment
import com.lesson.foodamy.ui.user.UserFragment

class MainPageAdapter(val fragment: Fragment): FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> RecipeCategoriesFragment()
            2 -> UserFragment()
            else-> HomeFragment()
        }
    }
}