package sopt.uni.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import sopt.uni.R

abstract class BindingDialogFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
    private val isWrapContent: Boolean = false,
) :
    DialogFragment() {
    private var _binding: B? = null
    val binding get() = requireNotNull(_binding!!) { "${this::class.java.simpleName}에서 에러가 발생했습니다." }

    override fun onStart() {
        super.onStart()
        val width = if (isWrapContent) ViewGroup.LayoutParams.WRAP_CONTENT else resources.getDimensionPixelSize(R.dimen.dialog_width)
        val height = if (isWrapContent) ViewGroup.LayoutParams.WRAP_CONTENT else resources.getDimensionPixelSize(R.dimen.dialog_height)
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
