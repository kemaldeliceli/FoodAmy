package com.lesson.foodamy.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.androidadvance.topsnackbar.TSnackbar
import com.lesson.foodamy.BR
import com.lesson.foodamy.R

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment(layoutResId) {
    private lateinit var snackbar: TSnackbar
    private lateinit var coordinate: CoordinatorLayout
    lateinit var viewModel: VM
    lateinit var binding: VDB


    abstract fun getViewModelss(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(getViewModelss())

        observeErrorMessage()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            when (viewModel.event.value) {
//                is BaseViewEvent.Navigate -> {
//
//                }
//                is BaseViewEvent.ShowError -> {
//                    setSnackbar((viewModel.event.value as BaseViewEvent.ShowError).msg)
//                }
//            }
//        }
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewEvent.Navigate -> {
                    findNavController().navigate(it.direction)
                }
                is BaseViewEvent.ShowError -> {
                    setSnackbar(it.msg)
                }
            }
        }
    }


    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(this, Observer<Int> { errorMessage ->
            setSnackbar(getString(errorMessage))
            println(getString(errorMessage))
        })
    }

    fun setCoordinateSnackbar(coordinate: CoordinatorLayout) {
        this.coordinate = coordinate
    }

    fun setSnackbar(message: String) {

        snackbar = TSnackbar.make(coordinate, message, TSnackbar.LENGTH_LONG)

        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.primary_red,
                null
            )
        )

        val textView: TextView =
            snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text)

        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.neutral_white, null))

        snackbar.show()
    }
}