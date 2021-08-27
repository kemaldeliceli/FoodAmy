package com.lesson.foodamy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.lesson.foodamy.adapter.OnboardingItemsAdapter
import com.lesson.foodamy.model.LoginActivity
import com.lesson.foodamy.model.OnboardingItem

class MainActivity : AppCompatActivity() {

    lateinit var onboardingItemAdapter: OnboardingItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardItems()

        val closeButton = findViewById<ImageView>(R.id.closeButton)
        closeButton.setOnClickListener {
            goToLoginPage()
        }
    }
    private fun setOnboardItems(){

        val dot1 = findViewById<ImageView>(R.id.dot1)
        val dot2 = findViewById<ImageView>(R.id.dot2)
        val dot3 = findViewById<ImageView>(R.id.dot3)
        val dot4 = findViewById<ImageView>(R.id.dot4)
        val selectorDotList = arrayListOf<ImageView>(dot1,dot2,dot3,dot4)
        val button = findViewById<Button>(R.id.nextButton)

        onboardingItemAdapter = OnboardingItemsAdapter(
            arrayListOf(
                OnboardingItem(R.drawable.first_walkthrough_image_1,"Welcome to Fodamy Network!","Fodamy is the best place to find your favorite recipes in all around the word."),
                OnboardingItem(R.drawable.first_walkthrough_image_2,"Finding recipes were not that easy.","Fodamy is the best place to find your favorite recipes in all around the word."),
                OnboardingItem(R.drawable.first_walkthrough_image_3,"Add new recipe.","Fodamy is the best place to find your favorite recipes in all around the word."),
                OnboardingItem(R.drawable.first_walkthrough_image_4,"Share recipes with others.","Fodamy is the best place to find your favorite recipes in all around the word.")
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPage)
        onboardingViewPager.adapter = onboardingItemAdapter
        var oldPosition = 1

        onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){


            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectorDotList[position].setImageResource(R.drawable.selected)
                selectorDotList[oldPosition].setImageResource(R.drawable.unselected)
                oldPosition = position
                if (position==3){
                    button.text = "Start!"
                }
                else{
                    button.text = "Next"
                }
                if(position<3) {
                    button.setOnClickListener {
                        onboardingViewPager.currentItem += 1
                    }
                }else{
                    button.setOnClickListener {
                        goToLoginPage()
                    }
                }


            }
        })
    }
    private fun goToLoginPage(){
        startActivity(Intent(applicationContext,LoginActivity::class.java))
        finish()
    }
}