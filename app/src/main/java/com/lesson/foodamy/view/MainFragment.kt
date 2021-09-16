package com.lesson.foodamy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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
        return binding.root
    }

}