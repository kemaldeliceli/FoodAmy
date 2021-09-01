package com.lesson.foodamy

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.fragment.app.Fragment
import com.androidadvance.topsnackbar.TSnackbar
import com.lesson.foodamy.databinding.FragmentLoginBinding
import com.lesson.foodamy.model.AuthData
import com.lesson.foodamy.services.AuthAPIService


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding :FragmentLoginBinding
    private lateinit var snackbar: TSnackbar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater,container,false)

        setListeners()

        return binding.root
    }
    private fun setSnackbar(message: String){
        snackbar = TSnackbar.make(binding.snackbarCoord,message,TSnackbar.LENGTH_LONG)

        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(getColor(resources,R.color.primary_red,null))

        val textView :TextView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text)

        textView.setTextColor(getColor(resources,R.color.neutral_white,null))
    }
    private fun setListeners(){
        binding.loginButton.setOnClickListener {
            val userAuth = AuthData(binding.userEmailEditText.text.toString(),binding.userPasswordEditText.text.toString())
            //val userAuth = AuthData("admin@fodamy.com","Fodamy@fodamy2017MST")
            val response = validateEmail(userAuth)
            if (response==true){
                AuthAPIService.requestAuth(userAuth)
            }
        }
    }
    private fun validateEmail(userAuth: AuthData): Boolean{

        if (userAuth.email.isEmpty()){
            setSnackbar(getString(R.string.empty_email_blank))
            snackbar.show()
            return false
        }
        if (userAuth.password.isEmpty()){
            setSnackbar(getString(R.string.empty_password_blank))
            snackbar.show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userAuth.email).matches()){
            setSnackbar(getString(R.string.wrong_format_email))
            snackbar.show()
            return false
        }

        return true
    }
}