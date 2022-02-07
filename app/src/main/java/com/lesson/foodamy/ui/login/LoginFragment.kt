package com.lesson.foodamy.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.R
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import com.lesson.foodamy.databinding.FragmentLoginBinding as FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(R.layout.fragment_login) {

    @Inject
    lateinit var loginSharedPref: IPrefDefaultManager


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
        binding.goToMain.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    private fun getResponseAndNavigate() {
        viewModel.responseMessage.observe(viewLifecycleOwner, { responseMessage ->
            when (responseMessage) {
                is BaseResponse.Success -> {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToMainFragment(responseMessage.data.user)
                    findNavController().navigate(action)
                    loginSharedPref.setUserInfo(responseMessage.data.user!!)

                }
                is BaseResponse.Error -> {
                    setSnackbar(responseMessage.error.error.toString())
                }
            }

        })

    }


    override fun getViewModelss(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }
}