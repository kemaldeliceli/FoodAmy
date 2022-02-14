package com.lesson.foodamy.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentFavouriteBinding


class FavouriteFragment : BaseFragment<FavouriteViewModel,FragmentFavouriteBinding>(R.layout.fragment_favourite) {
    private var count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            it.setBackgroundColor(R.color.white + count)
            count+=5
        }
    }

    override fun getViewModelss(): Class<FavouriteViewModel> {
        return FavouriteViewModel::class.java
    }


}