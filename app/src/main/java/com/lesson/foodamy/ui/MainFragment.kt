package com.lesson.foodamy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lesson.foodamy.R
import com.lesson.foodamy.adapter.MainPageAdapter
import com.lesson.foodamy.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    val args: MainFragmentArgs by navArgs()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.userInfo = args.responseUserInfo

        setAdapter()
        setBottomNavigationBar()
        setListeners()

        return binding.root
    }

    private fun setBottomNavigationBar() {
        binding.viewPager.isUserInputEnabled = false


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.favoruiteFragment -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                R.id.userFragment -> {
                    binding.viewPager.currentItem = 2
                    true
                }
                R.id.menuFragment -> {
                    binding.viewPager.currentItem = 3
                    true
                }

                else -> {
                    binding.viewPager.currentItem = 0
                    true
                }
            }
        }
    }

    private fun setAdapter() {
        binding.viewPager.adapter = MainPageAdapter(fragment = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topConstraintLayout.fodamyLogo.isVisible = true
        binding.topConstraintLayout.textViewTitle.isVisible = false
        binding.topConstraintLayout.imageButtonExit.setOnClickListener {
            exit()
        }
        //val bottomNavigationView = binding.bottomNavigationView
        //val navController = childFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        //bottomNavigationView.setupWithNavController(navController.navController)
    }

    private fun exit() {
        findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
    }

    fun setListeners() {

    }

}