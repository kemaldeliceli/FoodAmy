package com.lesson.foodamy.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lesson.foodamy.Preferences.IPrefDefaultManager
import com.lesson.foodamy.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    @Inject  lateinit var loginSharedPref: IPrefDefaultManager

    companion object{
        const val DELAY_MILLIS : Long = 3000
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startNavigationTimer()
    }

    private fun startNavigationTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            if(loginSharedPref.isAppFirstOpen()) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_introFragment)
            }

            requireActivity().window.decorView.setBackgroundColor(ResourcesCompat.getColor(resources,
                R.color.primary_background,null))
        }, DELAY_MILLIS)
    }
}