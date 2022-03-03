package com.lesson.foodamy.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.lesson.foodamy.R
import com.lesson.foodamy.databinding.CustomTopBarBinding

class CustomViewTopBar @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
)

 : ConstraintLayout(context,attrs,defStyle) {

    interface BackButtonClick{
        fun onClick()
    }
    interface LogoutButtonClick{
        fun onClick()
    }

    var backListener: BackButtonClick? = null
    var logoutListener: LogoutButtonClick? = null
    private var binding:CustomTopBarBinding
    init {
        val inflater =LayoutInflater.from(context)
        binding = CustomTopBarBinding.inflate(inflater, this,true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomViewTopBar)


        try{
            if(attributes.hasValue(R.styleable.CustomViewTopBar_shareIconVisibility)){
                binding.shareIcon.isInvisible = !attributes.
                getBoolean(R.styleable.CustomViewTopBar_shareIconVisibility,false)
            }
            if(attributes.hasValue(R.styleable.CustomViewTopBar_logOutIconVisibility)){
                val logoutVisibility = attributes.getBoolean(R.styleable.CustomViewTopBar_logOutIconVisibility,
                    false)
                binding.logoutIcon.isVisible = logoutVisibility

                if (logoutVisibility){
                    binding.logoutIcon.setOnClickListener {
                        logoutListener?.onClick()
                    }
                }
            }
            if(attributes.hasValue(R.styleable.CustomViewTopBar_fodamyLogoVisibility)){
                binding.fodamyLogo.isVisible = attributes.
                getBoolean(R.styleable.CustomViewTopBar_fodamyLogoVisibility,false)
            }
            if(attributes.hasValue(R.styleable.CustomViewTopBar_backButtonVisibility)){
                val backButtonVisibility =  attributes.getBoolean(R.styleable.CustomViewTopBar_backButtonVisibility,false)

                binding.backConstraint.isVisible = backButtonVisibility
                if (backButtonVisibility){
                    binding.backConstraint.setOnClickListener {
                        backListener?.onClick()
                    }
                }

            }
            if(attributes.hasValue(R.styleable.CustomViewTopBar_pageTitleVisibility)){
                val pageTitleVisibility = attributes.getBoolean(R.styleable.CustomViewTopBar_pageTitleVisibility,false)
                binding.titleText.isVisible = pageTitleVisibility
            }
            if (attributes.hasValue(R.styleable.CustomViewTopBar_pageTitle)){
                setTitle(attributes.getString(R.styleable.CustomViewTopBar_pageTitle))
            }

        }finally {
            attributes.recycle()
        }
    }
    fun setTitle(title: String?) {
        binding.apply {
         titleText.text = title
        }
    }
}