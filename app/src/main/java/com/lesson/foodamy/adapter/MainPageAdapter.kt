package com.lesson.foodamy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lesson.foodamy.view.mainpageviews.FavoruiteFragment
import com.lesson.foodamy.view.mainpageviews.HomeFragment
import com.lesson.foodamy.view.mainpageviews.MenuFragment
import com.lesson.foodamy.view.mainpageviews.UserFragment

class MainPageAdapter(fragmentManager:  Fragment): FragmentStateAdapter(fragmentManager) {


    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> HomeFragment()
            1-> FavoruiteFragment()
            2-> UserFragment()
            3-> MenuFragment()
            else-> HomeFragment()
        }
    }

}