package com.lesson.foodamy.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.adapter.MainPageAdapter
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentMainBinding


class MainFragment : BaseFragment<MainViewModel,FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setBottomNavigationBar()
        setListeners()
    }

    private fun setBottomNavigationBar() {
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.isMotionEventSplittingEnabled = false


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    //binding.viewPager.currentItem = 0
                    binding.viewPager.setCurrentItem(0, false)
                    true
                }
                R.id.favoruiteFragment -> {
                    //                   binding.viewPager.currentItem = 1
                    binding.viewPager.setCurrentItem(1, false)
                    true
                }
                R.id.userFragment -> {
                    //                 binding.viewPager.currentItem = 2
                    binding.viewPager.setCurrentItem(2, false)

                    true
                }
                R.id.menuFragment -> {
//                    binding.viewPager.currentItem = 3
                    binding.viewPager.setCurrentItem(3, false)

                    true
                }

                else -> {
//                    binding.viewPager.currentItem = 0
                    binding.viewPager.setCurrentItem(0, false)
                    true
                }
            }
        }
    }

    private fun setAdapter() {
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.adapter = MainPageAdapter(fragment = this)
    }

    fun setListeners() {
        binding.logoutIcon.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    override fun getViewModelss(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}