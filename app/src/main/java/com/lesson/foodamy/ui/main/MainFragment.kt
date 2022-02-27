package com.lesson.foodamy.ui.main

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setBottomNavigationBar()
        setCoordinateSnackbar(binding.snackbarCoord)
    }

    private fun setBottomNavigationBar() {
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.isMotionEventSplittingEnabled = false

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    binding.viewPager.setCurrentItem(0, false)
                    true
                }
                R.id.favoruiteFragment -> {
                    binding.viewPager.setCurrentItem(1, false)
                    true
                }
                R.id.userFragment -> {
                    binding.viewPager.setCurrentItem(2, false)
                    true
                }

                else -> {
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

    override fun getViewModelss(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}