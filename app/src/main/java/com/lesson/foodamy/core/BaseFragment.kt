package com.lesson.foodamy.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.androidadvance.topsnackbar.TSnackbar
import com.lesson.foodamy.BR
import com.lesson.foodamy.R

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) :

    Fragment(layoutResId) {
    private lateinit var snackbar: TSnackbar
    private lateinit var coordinate: CoordinatorLayout
    private lateinit var progressBar: ProgressBar

    lateinit var viewModel: VM
    lateinit var binding: VDB

    abstract fun getViewModelss(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(getViewModelss())
        progressBar = requireActivity().findViewById(R.id.main_progress_bar)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        setLoadingObserver()


        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun setLoadingObserver() {
        viewModel.loading.observe(viewLifecycleOwner,{
            progressBar.isVisible = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()
    }


    private fun handleEvent() {
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewEvent.Navigate -> {
                    navigate(it.directions)
                }
                is BaseViewEvent.ShowMessage -> {
                    when (it.msg) {
                        is Int -> {
                            setSnackbar(getString(it.msg))
                        }
                        is String -> {
                            setSnackbar(it.msg)
                        }
                    }
                }
                is BaseViewEvent.ShowAlertDialog -> {
                    when (it.msg) {
                        is Int -> {
                            showAlertDialog(getString(it.msg), it.directions)
                        }
                        is String -> {
                            showAlertDialog(it.msg, it.directions)
                        }
                    }
                }
                is BaseViewEvent.PopBackStack -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun showAlertDialog(msg: String, directions: NavDirections) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alert_dialog_layout)

        val body = dialog.findViewById(R.id.warningText) as TextView
        body.text = msg

        val loginButton = dialog.findViewById(R.id.dialog_login_button) as Button
        val cancelButton = dialog.findViewById(R.id.dialog_cancel_button) as Button
        loginButton.setOnClickListener {
            navigate(directions)
            dialog.dismiss()
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
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


    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}
