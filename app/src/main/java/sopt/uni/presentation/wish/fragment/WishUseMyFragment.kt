package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentWishUseMyBinding
import sopt.uni.presentation.wish.WishFcViewModel

@AndroidEntryPoint
class WishUseMyFragment : Fragment() {
    private var _binding: FragmentWishUseMyBinding? = null
    private val wishFcViewModel: WishFcViewModel by viewModels()
    private var wishCouponId: Int = 0
    private val binding: FragmentWishUseMyBinding
        get() = requireNotNull(_binding) { "binding is null" }

    companion object {
        fun newInstance(wishCouponId: Int): WishUseMyFragment {
            val fragment = WishUseMyFragment()
            val args = Bundle()
            args.putInt("wishCouponId", wishCouponId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWishUseMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = wishFcViewModel

        val bundle = arguments
        if (bundle != null) {
            wishCouponId = bundle.getInt("wishCouponId")
        }
        Log.d("wishCouponId", "$wishCouponId")

        wishFcViewModel.setWishDetailData(wishCouponId)

        with(binding) {
            btnWishUseMyFinish.setOnClickListener {
                useWishDialog()
            }

            btnWishUseMyBack.setOnClickListener {
                activity?.finish()
            }
            Log.d("wishCouponContent", "${wishFcViewModel.wishCouponContent.value}")
        }
//        binding.tvWishUseMyDescription.text = wishFcViewModel.wishCouponContent.value
    }

    private fun useWishDialog() {
        UseWishDialogFragment().apply {
            titleText = this@WishUseMyFragment.resources.getString(R.string.use_wish_dialog_title)
            bodyText =
                this@WishUseMyFragment.resources.getString(R.string.use_wish_dialog_description)
            confirmButtonText = this@WishUseMyFragment.resources.getString(R.string.dialog_ok_text)
            dismissButtonText =
                this@WishUseMyFragment.resources.getString(R.string.dialog_cancel_text)
            confirmClickListener = {
                val fragment = WishUsedFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_wish_fc, fragment).commit()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(parentFragmentManager, "")
    }
}
