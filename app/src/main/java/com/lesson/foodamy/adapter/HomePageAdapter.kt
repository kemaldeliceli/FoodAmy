package com.lesson.foodamy.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lesson.foodamy.ui.editorschoice.EditorsChoiceFragment
import com.lesson.foodamy.ui.recentlyadded.RecentlyAddedFragment


class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> EditorsChoiceFragment()
            1 -> RecentlyAddedFragment()
            else-> EditorsChoiceFragment()
        }
    }

}