package com.lesson.foodamy.view.mainpageviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.lesson.foodamy.adapter.FragmentAdapter
import com.lesson.foodamy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        setAdapter()


        return binding.root
    }

    fun setAdapter(){
        binding.viewPager.adapter = FragmentAdapter(fragment = this)

        TabLayoutMediator(binding.topTab,binding.viewPager){tab,position ->
            when(position){
                0->tab.text = "EDITORS' CHOICE"
                1->tab.text = "RECENTLY ADDED"
            }
        }.attach()
    }

}