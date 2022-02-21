package com.lesson.foodamy.core


import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
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

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment(layoutResId) {
    private lateinit var snackbar: TSnackbar
    private lateinit var coordinate: CoordinatorLayout
    lateinit var viewModel: VM
    lateinit var binding: VDB

    abstract fun getViewModelss(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(getViewModelss())
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()
    }

    private fun handleEvent() {
        viewModel.event.observe(viewLifecycleOwner,{
            when(it){
                is BaseViewEvent.Navigate -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        navigate(it.directions)}, 300)
                }
                is BaseViewEvent.ShowMessage -> {
                    when(it.msg){
                        is Int -> {setSnackbar(getString(it.msg))}
                        is String -> { setSnackbar(it.msg) }
                    }

                }
                is BaseViewEvent.ShowAlertDialog -> {
                    when(it.msg){
                        is Int -> {showAlertDialog(getString(it.msg), it.directions)}
                        is String -> { showAlertDialog(it.msg, it.directions) }
                    }
                }
                BaseViewEvent.PopBackStack -> {
                    findNavController().popBackStack()
                }
            }

        })
    }

    private fun showAlertDialog(msg: String, directions: NavDirections) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alert_dialog_layout)
        val body = dialog.findViewById(R.id.warningText) as TextView
        body.text = msg
        val yesBtn = dialog.findViewById(R.id.dialog_login_button) as Button
        val noBtn = dialog.findViewById(R.id.dialog_cancel_button) as Button
        yesBtn.setOnClickListener {
            navigate(directions)
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.setVariable(BR.viewModel,viewModel)
        return binding.root
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

    fun navigate(directions:NavDirections){
        findNavController().navigate(directions)
    }
}