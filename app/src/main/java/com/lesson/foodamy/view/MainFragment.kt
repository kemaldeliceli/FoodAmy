package com.lesson.foodamy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.lesson.foodamy.R
import com.lesson.foodamy.adapter.MainPageAdapter
import com.lesson.foodamy.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    val args:MainFragmentArgs by navArgs()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.userInfo = args.responseUserInfo

        setListeners()
        setViewPager()

        //ViewPager Page Changing Control
        binding.mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.nav_home_icon
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.nav_fav_icon
                    2 -> binding.bottomNavigationView.selectedItemId = R.id.nav_user_icon
                    3 -> binding.bottomNavigationView.selectedItemId = R.id.nav_menu_icon
                }
            }
        })
        binding.bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.nav_home_icon -> binding.mainViewPager.currentItem = 0
                R.id.nav_fav_icon -> binding.mainViewPager.currentItem = 1
                R.id.nav_user_icon -> binding.mainViewPager.currentItem = 2
                R.id.nav_menu_icon -> binding.mainViewPager.currentItem = 3
            }
            return@setOnItemSelectedListener true
        }

        return binding.root
    }

    fun setListeners(){
        binding.logoutIcon.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }
    fun setViewPager(){
        binding.mainViewPager.adapter = MainPageAdapter(this)
    }

    fun setBottomNavigation(){
        binding.mainViewPager.currentItem = binding.bottomNavigationView.selectedItemId
    }

}