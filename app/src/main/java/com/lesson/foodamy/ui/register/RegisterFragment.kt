package com.lesson.foodamy.ui.register

import android.os.Bundle
import android.view.View
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<RegisterViewModel, FragmentRegisterBinding>(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCoordinateSnackbar(binding.snackbarCoord)
    }

    override fun getViewModelss(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }
}
