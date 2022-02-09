package com.lesson.foodamy.ui.user

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentUserBinding
import com.lesson.foodamy.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<UserViewModel, FragmentUserBinding>(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewModel.getUserInfoFromApi()
        checkLogin()
        setListeners()
    }

    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    private fun checkLogin() {
        when(viewModel.userInfo.value){
            null -> {
                binding.notLoginUserPage.isVisible = true
                binding.boxes.isVisible = false
            }
            else -> {
                binding.notLoginUserPage.isVisible = false
                binding.boxes.isVisible = true
            }
        }
    }

    override fun getViewModelss(): Class<UserViewModel> {
        return  UserViewModel::class.java
    }

}