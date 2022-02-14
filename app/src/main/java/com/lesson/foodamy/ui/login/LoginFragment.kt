package com.lesson.foodamy.ui.login

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.lesson.foodamy.databinding.FragmentLoginBinding as FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCoordinateSnackbar(binding.snackbarCoord)
    }


    override fun getViewModelss(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }
}