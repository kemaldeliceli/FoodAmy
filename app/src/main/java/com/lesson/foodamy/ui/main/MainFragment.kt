package com.lesson.foodamy.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.adapter.MainPageAdapter
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentMainBinding
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.preferences.IPrefDefaultManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(R.layout.fragment_main) {
    @Inject
    lateinit var loginSharedPreferences: IPrefDefaultManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCoordinateSnackbar(binding.snackbarCoord)
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
        viewModel.responseLogout.observe(viewLifecycleOwner, { logoutResponse ->
            when (logoutResponse) {
                is BaseResponse.Error -> {setSnackbar(logoutResponse.error.error.toString())}
                is BaseResponse.Success -> {
                    setSnackbar("Successfull Logout")
                    loginSharedPreferences.saveLogin(isLogged = false)
                    findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                }
            }})
    }

    override fun getViewModelss(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}