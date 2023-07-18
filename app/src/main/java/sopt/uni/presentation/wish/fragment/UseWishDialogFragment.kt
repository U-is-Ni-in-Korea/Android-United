package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment

class UseWishDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {

    var titleText: String? = null
    var bodyText: String? = null
    var confirmButtonText: String? = null
    var dismissButtonText: String? = null
    var confirmClickListener: (() -> Unit)? = null
    var dismissClickListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
//        with(binding) {
//            dialogTitle.setText(getString(R.string.use_wish_dialog_title))
//            dialogBody.setText(getString(R.string.use_wish_dialog_description))
//            btnRight.setText(getString(R.string.dialog_ok_text))
//            btnLeft.setOnSingleClickListener {
//                dismiss()
//            }
//            btnRight.setOnSingleClickListener {
//                val fragment = WishUsedFragment()
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.fcv_wish_fc, fragment).commit()
//            }
//        }
    }

    private fun initView() {
        titleText?.let {
            binding.dialogTitle.visibility = View.VISIBLE
            binding.dialogTitle.text = it
        } ?: kotlin.run {
            binding.dialogTitle.visibility = View.GONE
        }
        bodyText?.let {
            binding.dialogBody.visibility = View.VISIBLE
            binding.dialogBody.text = it
        } ?: kotlin.run {
            binding.dialogBody.visibility = View.GONE
        }
        confirmButtonText?.let {
            binding.btnRight.text = it
        }
        dismissButtonText?.let {
            binding.btnLeft.text = it
        }

        confirmClickListener?.let {
            binding.btnRight.visibility = View.VISIBLE
            binding.btnRight.setOnClickListener {
                confirmClickListener?.invoke()
            }
        } ?: kotlin.run {
            binding.btnRight.visibility = View.GONE
        }

        dismissClickListener?.let {
            binding.btnLeft.visibility = View.VISIBLE
            binding.btnLeft.setOnClickListener {
                dismissClickListener?.invoke()
            }
        } ?: run {
            binding.btnLeft.visibility = View.GONE
        }
    }
}
