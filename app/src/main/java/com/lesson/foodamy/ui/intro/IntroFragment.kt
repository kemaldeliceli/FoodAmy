package com.lesson.foodamy.ui.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentIntroBinding
import com.lesson.foodamy.model.dataclass.OnboardItem
import com.lesson.foodamy.preferences.IPrefDefaultManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : BaseFragment<IntroViewModel, FragmentIntroBinding>(R.layout.fragment_intro) {

    private lateinit var onboardItemAdapter: OnboardItemsAdapter

    @Inject
    lateinit var loginSharedPref: IPrefDefaultManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnboardItems()
        setAdapter()
        setListeners()

        loginSharedPref.saveAppOpened()
    }

    private fun setListeners() {
        // Close Icon Listener
        binding.closeButton.setOnClickListener {
            goToLoginPage()
        }

        // Next Button Listener
        binding.nextButton.setOnClickListener {
            if (binding.onboardingViewPager.currentItem < 3)
                binding.onboardingViewPager.currentItem += 1
            else
                goToLoginPage()
        }

        // ViewPager Page Changing Control
        binding.onboardingViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == 3) {
                        binding.nextButton.text = getString(R.string.start_text)
                    } else {
                        binding.nextButton.text = getString(R.string.next_text)
                    }
                }
            })
    }

    private fun setOnboardItems() {
        onboardItemAdapter = OnboardItemsAdapter(
            arrayListOf(
                OnboardItem(
                    R.drawable.first_walkthrough_image_1, getString(R.string.title_1),
                    getString(
                        R.string.description_1
                    )
                ),
                OnboardItem(
                    R.drawable.first_walkthrough_image_2, getString(R.string.title_2),
                    getString(
                        R.string.description_2
                    )
                ),
                OnboardItem(
                    R.drawable.first_walkthrough_image_3, getString(R.string.title_3),
                    getString(
                        R.string.description_3
                    )
                ),
                OnboardItem(
                    R.drawable.first_walkthrough_image_4, getString(R.string.title_4),
                    getString(
                        R.string.description_3
                    )
                )
            )
        )
    }

    private fun setAdapter() {
        binding.onboardingViewPager.adapter = onboardItemAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.onboardingViewPager
        ) { _, _ -> }.attach()
    }

    private fun goToLoginPage() {
        findNavController().navigate(R.id.action_introFragment_to_mainFragment2)
    }

    override fun getViewModelss(): Class<IntroViewModel> {
        return IntroViewModel::class.java
    }
}
