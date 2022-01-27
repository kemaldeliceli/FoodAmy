package com.lesson.foodamy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.lesson.foodamy.R
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = binding.bottomNavigationView
        val navController = childFragmentManager.findFragmentById(binding.fragmentContainerView .id) as NavHostFragment
        
        bottomNavigationView.setupWithNavController(navController.navController)
    }

    fun setListeners(){
        binding.logoutIcon.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

}