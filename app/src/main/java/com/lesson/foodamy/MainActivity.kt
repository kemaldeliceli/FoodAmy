package com.lesson.foodamy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lesson.foodamy.adapter.OnboardingItemsAdapter
import com.lesson.foodamy.model.OnboardingItem

class MainActivity : AppCompatActivity() {

    lateinit var onboardingItemAdapter: OnboardingItemsAdapter
    lateinit var onboardingViewPager:ViewPager2
    lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onboardingViewPager = findViewById(R.id.onboardingViewPage)
        nextButton = findViewById(R.id.nextButton)

        setOnboardItems()
        setAdapter()
        setListeners()

    }

    private fun setListeners(){
        val closeButton = findViewById<ImageView>(R.id.closeButton)

        // Close Icon Listener
        closeButton.setOnClickListener {
            goToLoginPage()
        }

        // Next Button Listener
        nextButton.setOnClickListener {
            if(onboardingViewPager.currentItem<3)
                onboardingViewPager.currentItem += 1
            else
                goToLoginPage()
        }

        //ViewPager Page Changing Control
        onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position==3){
                    nextButton.text =getString( R.string.start_text)
                }
                else{
                    nextButton.text = getString(R.string.next_text)
                }
            }
        })

    }

    private fun setOnboardItems(){
        onboardingItemAdapter = OnboardingItemsAdapter(
            arrayListOf(
                OnboardingItem(R.drawable.first_walkthrough_image_1,getString(R.string.title_1),getString(R.string.description_1)),
                OnboardingItem(R.drawable.first_walkthrough_image_2,getString(R.string.title_2),getString(R.string.description_2)),
                OnboardingItem(R.drawable.first_walkthrough_image_3,getString(R.string.title_3),getString(R.string.description_3)),
                OnboardingItem(R.drawable.first_walkthrough_image_4,getString(R.string.title_4),getString(R.string.description_3))
            )
        )
    }
    private fun setAdapter(){
        onboardingViewPager.adapter = onboardingItemAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, onboardingViewPager){ tab, position -> }.attach()
    }
    private fun goToLoginPage(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
}