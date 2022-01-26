package com.lesson.foodamy.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lesson.foodamy.view.EditorsChoiceFragment
import com.lesson.foodamy.view.RecentlyAddedFragment


class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> EditorsChoiceFragment.newInstance()
            1 -> RecentlyAddedFragment.newInstance()
            else-> EditorsChoiceFragment.newInstance()
        }
    }

}