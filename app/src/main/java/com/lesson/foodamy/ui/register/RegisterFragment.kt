package com.lesson.foodamy.ui.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentRegisterBinding
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getResponseAndNavigate()
        setCoordinateSnackbar(binding.snackbarCoord)
        setListeners()
    }

    private fun setListeners() {
        binding.loginText.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }


    private fun getResponseAndNavigate() {
        viewModel.responseMessage.observe(viewLifecycleOwner, Observer<BaseResponse<Any>>{responseMessage ->
            when(responseMessage){
                is BaseResponse.Success -> {}
                is BaseResponse.Error -> {
                    setSnackbar(responseMessage.error.error.toString())
                }
            }
        })
    }

    override fun getViewModelss(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

}