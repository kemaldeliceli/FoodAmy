package com.lesson.foodamy.ui.user

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<UserViewModel, FragmentUserBinding>(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun getViewModelss(): Class<UserViewModel> {
        return UserViewModel::class.java
    }
}
