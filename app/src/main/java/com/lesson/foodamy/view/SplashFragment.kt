package com.lesson.foodamy.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.LoginSharedPref
import com.lesson.foodamy.R

class SplashFragment : Fragment() {

    private  val loginSharedPref = LoginSharedPref()

    companion object{
        const val DELAY_MILLIS : Long = 3000
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginSharedPref.invoke(requireContext())
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startNavigationTimer()
    }

    private fun startNavigationTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            if(loginSharedPref.getAppOpened()!!) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_introFragment)
            }

            requireActivity().window.decorView.setBackgroundColor(ResourcesCompat.getColor(resources,
                R.color.primary_background,null))
        }, DELAY_MILLIS)
    }
}