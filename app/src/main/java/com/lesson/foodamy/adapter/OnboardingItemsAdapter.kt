package com.lesson.foodamy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.lesson.foodamy.R
import com.lesson.foodamy.model.OnboardingItem

class OnboardingItemsAdapter(private var onboardingItems: ArrayList<OnboardingItem>):
RecyclerView.Adapter<OnboardingItemsAdapter.OnBoardingItemViewHolder>()
{
    inner class OnBoardingItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var imageOnboarding = view.findViewById<ImageView>(R.id.firstWalkthroughImage)
        private var descriptionHeader = view.findViewById<TextView>(R.id.descriptionHeader)
        private var description = view.findViewById<TextView>(R.id.description)

        fun bind(onboardingItem: OnboardingItem){
            imageOnboarding.setImageResource(onboardingItem.image)
            descriptionHeader.text = onboardingItem.title
            description.text = onboardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
            return OnBoardingItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.slide_screen,
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }
}