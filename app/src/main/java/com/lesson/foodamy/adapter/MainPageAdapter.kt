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
            0-> HomeFragment.newInstance()
            1-> FavoruiteFragment.newInstance()
            2-> UserFragment.newInstance()
            3-> MenuFragment.newInstance()
            else-> HomeFragment.newInstance()
        }
    }

}