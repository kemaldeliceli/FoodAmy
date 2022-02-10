package com.lesson.foodamy.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lesson.foodamy.ui.favourite.FavouriteFragment
import com.lesson.foodamy.ui.home.HomeFragment
import com.lesson.foodamy.ui.menu.MenuFragment
import com.lesson.foodamy.ui.user.UserFragment

class MainPageAdapter(val fragment: Fragment): FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> FavouriteFragment()
            2 -> UserFragment()
            3 -> MenuFragment()
            else-> HomeFragment()
        }
    }
}