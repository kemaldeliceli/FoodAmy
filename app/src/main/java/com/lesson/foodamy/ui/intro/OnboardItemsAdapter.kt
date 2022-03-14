package com.lesson.foodamy.ui.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lesson.foodamy.R
import com.lesson.foodamy.model.dataclass.OnboardItem

class OnboardItemsAdapter(private var onboardItems: ArrayList<OnboardItem>) :
    RecyclerView.Adapter<OnboardItemsAdapter.OnBoardingItemViewHolder>() {
    inner class OnBoardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imageOnboard = view.findViewById<ImageView>(R.id.firstWalkthroughImage)
        private var descriptionHeader = view.findViewById<TextView>(R.id.descriptionHeader)
        private var description = view.findViewById<TextView>(R.id.description)

        fun bind(onboardItem: OnboardItem) {
            imageOnboard.setImageResource(onboardItem.image)
            descriptionHeader.text = onboardItem.title
            description.text = onboardItem.description
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
        holder.bind(onboardItems[position])
    }

    override fun getItemCount(): Int {
        return onboardItems.size
    }
}
