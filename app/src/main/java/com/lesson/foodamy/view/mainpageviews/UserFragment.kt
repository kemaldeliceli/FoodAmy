package com.lesson.foodamy.view.mainpageviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lesson.foodamy.Preferences.IPrefDefaultManager
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.FragmentUserBinding
import com.lesson.foodamy.model.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@AndroidEntryPoint

class UserFragment : Fragment() {

    @Inject
    lateinit var loginSharedPref: IPrefDefaultManager
    lateinit var binding: FragmentUserBinding
    lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container,false)

        userInfo = loginSharedPref.getUserInfo()
        binding.userInfo = this.userInfo
        return binding.root
    }

}