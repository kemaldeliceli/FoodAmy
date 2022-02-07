package com.lesson.foodamy.ui.login

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.R
import com.lesson.foodamy.core.BaseFragment
import com.lesson.foodamy.databinding.FragmentLoginBinding
import com.lesson.foodamy.model.BaseResponse
import com.lesson.foodamy.preferences.IPrefDefaultManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
        val text = "lets sign up";
        val spannable = SpannableString(text)
        spannable.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }

            }, 4, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE

        )
        binding.signUpText.text = spannable.toString()
        binding.signUpText.setOnClickListener {

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