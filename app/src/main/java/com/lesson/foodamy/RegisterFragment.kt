package com.lesson.foodamy

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.androidadvance.topsnackbar.TSnackbar
import com.lesson.foodamy.databinding.FragmentRegisterBinding
import com.lesson.foodamy.model.RegisterData
import com.lesson.foodamy.services.RegisterAPIService


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var snackbar: TSnackbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.loginText.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.signUpButton.setOnClickListener {
            val userRegisterInfo = RegisterData(
                binding.userEmailEditText.text.toString(),
                binding.userPasswordEditText.text.toString(),
                binding.usernameEditText.text.toString()
            )

                if(validateEmail(userRegisterInfo)){
                    val responseMessage = RegisterAPIService.requestRegister(userRegisterInfo)
                    println(responseMessage.errorMessage)

                    responseMessage.errorMessage?.let {
                        setSnackbar(it)
                        snackbar.show()
                    }

                }
            }
        }

    private fun setSnackbar(message: String){
        snackbar = TSnackbar.make(binding.snackbarCoord,message, TSnackbar.LENGTH_LONG)

        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.primary_red,
                null
            )
        )

        val textView : TextView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text)

        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.neutral_white, null))
    }

    private fun validateEmail(registerData: RegisterData): Boolean{

        if (registerData.email.isEmpty()){
            setSnackbar(getString(R.string.empty_email_blank))
            snackbar.show()
            return false
        }
        if (registerData.password.isEmpty()){
            setSnackbar(getString(R.string.empty_password_blank))
            snackbar.show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(registerData.email).matches()){
            setSnackbar(getString(R.string.wrong_format_email))
            snackbar.show()
            return false
        }
        if (registerData.username.isEmpty()){
            setSnackbar(getString(R.string.empty_username_blank))
            snackbar.show()
            return false
        }


        return true
    }
}