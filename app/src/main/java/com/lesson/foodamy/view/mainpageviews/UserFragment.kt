package com.lesson.foodamy.view.mainpageviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lesson.foodamy.Preferences.IPrefDefaultManager
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentUserBinding
import com.lesson.foodamy.model.UserInfo
import com.lesson.foodamy.view.BaseFragment
import com.lesson.foodamy.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : BaseFragment<UserViewModel,FragmentUserBinding>(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo()
        setListeners()
    }

    private fun setListeners(){
        viewModel.userInfo.observe(viewLifecycleOwner,{ userInfo->
                binding.userInfo = userInfo
            }
        )
    }

    override fun getViewModelss(): Class<UserViewModel> {
        return  UserViewModel::class.java
    }

}