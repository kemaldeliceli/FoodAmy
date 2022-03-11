package com.lesson.foodamy.ui.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }
    fun setAdapter() {
        binding.viewPager.adapter = HomePageAdapter(fragment = this)
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.topTab, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "EDITORS' CHOICE"
                1 -> tab.text = "RECENTLY ADDED"
            }
        }.attach()
    }

    override fun getViewModelss(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}
