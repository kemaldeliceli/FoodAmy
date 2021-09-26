package com.lesson.foodamy.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.viewmodel.LoginViewModel
import com.lesson.foodamy.databinding.FragmentLoginBinding as FragmentLoginBinding


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getResponseAndNavigate()
        setListeners()
        setCoordinateSnackbar(binding.snackbarCoord)
    }

    private fun setListeners() {
        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun getResponseAndNavigate() {
        viewModel.responseMessage.observe(viewLifecycleOwner, { responseMessage ->
            when (responseMessage) {
                is BaseResponse.Success -> {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToMainFragment(responseMessage.data.user)
                    findNavController().navigate(action)
                }
                is BaseResponse.Error -> {
                    setSnackbar(responseMessage.error.error.toString())
                }
            }

        })

    }

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }
}