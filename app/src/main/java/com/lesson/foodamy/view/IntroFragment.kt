package com.lesson.foodamy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.lesson.foodamy.Preferences.IPrefDefaultManager
import com.lesson.foodamy.R
import com.lesson.foodamy.adapter.OnboardingItemsAdapter
import com.lesson.foodamy.databinding.FragmentIntroBinding
import com.lesson.foodamy.model.dataclass.OnboardingItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : Fragment() {

    private lateinit var onboardingItemAdapter: OnboardingItemsAdapter
    private lateinit var binding: FragmentIntroBinding
    @Inject lateinit var loginSharedPref: IPrefDefaultManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater,container,false)

        setOnboardItems()
        setAdapter()
        setListeners()

        loginSharedPref.saveAppOpened()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setListeners(){
        // Close Icon Listener
        binding.closeButton.setOnClickListener {
            goToLoginPage()
        }

        // Next Button Listener
        binding.nextButton.setOnClickListener {
            if(binding.onboardingViewPager.currentItem<3)
                binding.onboardingViewPager.currentItem += 1
            else
                goToLoginPage()
        }

        //ViewPager Page Changing Control
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position==3){
                    binding.nextButton.text =getString(R.string.start_text)
                }
                else{
                    binding.nextButton.text = getString(R.string.next_text)
                }
            }
        })
    }

    private fun setOnboardItems(){
        onboardingItemAdapter = OnboardingItemsAdapter(
            arrayListOf(
                OnboardingItem(
                    R.drawable.first_walkthrough_image_1,getString(R.string.title_1),getString(
                        R.string.description_1
                    )),
                OnboardingItem(
                    R.drawable.first_walkthrough_image_2,getString(R.string.title_2),getString(
                        R.string.description_2
                    )),
                OnboardingItem(
                    R.drawable.first_walkthrough_image_3,getString(R.string.title_3),getString(
                        R.string.description_3
                    )),
                OnboardingItem(
                    R.drawable.first_walkthrough_image_4,getString(R.string.title_4),getString(
                        R.string.description_3
                    ))
            )
        )
    }
    private fun setAdapter(){
        binding.onboardingViewPager.adapter = onboardingItemAdapter
        TabLayoutMediator(binding.tabLayout, binding.onboardingViewPager){ tab, position -> }.attach()
    }
    private fun goToLoginPage(){
        findNavController().navigate(R.id.action_introFragment_to_loginFragment)
    }
}