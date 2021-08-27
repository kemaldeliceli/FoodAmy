package com.lesson.foodamy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.lesson.foodamy.adapter.OnboardingItemsAdapter
import com.lesson.foodamy.model.OnboardingItem


/**
 * A simple [Fragment] subclass.
 * Use the [IntroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntroFragment : Fragment() {

    lateinit var onboardingItemAdapter: OnboardingItemsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnboardItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }
    private fun setOnboardItems(){
        onboardingItemAdapter = OnboardingItemsAdapter(
            arrayListOf(
                OnboardingItem(R.drawable.first_walkthrough_image_1,"Welcome to Fodamy Network!","Fodamy is the best place to find your favorite recipes in all around the word."),
                OnboardingItem(R.drawable.first_walkthrough_image_2,"Finding recipes were not that easy.","Fodamy is the best place to find your favorite recipes in all around the word."),
                OnboardingItem(R.drawable.first_walkthrough_image_3,"Add new recipe.","Fodamy is the best place to find your favorite recipes in all around the word."),
                OnboardingItem(R.drawable.first_walkthrough_image_4,"Share recipes with others.","Fodamy is the best place to find your favorite recipes in all around the word.")
            )
        )

        println(onboardingItemAdapter.itemCount)
        println(onboardingItemAdapter==null)

        val onboardingViewPager = activity?.findViewById<ViewPager2>(R.id.onboardingViewPage)
        val textView = activity?.findViewById<TextView>(R.id.textView)
        textView?.text = "halo"
        onboardingItemAdapter.let { onboardingViewPager?.adapter = it }
    }
}