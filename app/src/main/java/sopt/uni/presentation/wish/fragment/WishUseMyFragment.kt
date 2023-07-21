package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentWishUseMyBinding
import sopt.uni.presentation.wish.WishActivity
import sopt.uni.presentation.wish.WishFcViewModel

@AndroidEntryPoint
class WishUseMyFragment : Fragment() {
    private var _binding: FragmentWishUseMyBinding? = null
    private val wishFcViewModel: WishFcViewModel by viewModels()
    private var wishCouponId: Int = 0
    private var wishCouponImage: String = ""
    private var wishCouponIsUsed: Boolean = true
    private val binding: FragmentWishUseMyBinding
        get() = requireNotNull(_binding) { "binding is null" }

    companion object {
        fun newInstance(wishCouponId: WishActivity.WishTypeId): WishUseMyFragment {
            val fragment = WishUseMyFragment()
            val args = Bundle()
            args.putInt("wishCouponId", wishCouponId.id)
            args.putBoolean("isUsed", wishCouponId.isUsed)
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
        binding.lifecycleOwner = this.viewLifecycleOwner

        val bundleId = arguments
        if (bundleId != null) {
            wishCouponId = bundleId.getInt("wishCouponId")
        }

        wishFcViewModel.setWishDetailData(wishCouponId)

        val bundleIsUsed = arguments
        if (bundleIsUsed != null) {
            wishCouponIsUsed = bundleIsUsed.getBoolean("isUsed")
        }

        with(binding) {
            if (wishCouponIsUsed == true) {
                btnWishUseMyFinish.isEnabled = false
                btnWishUseMyFinish.backgroundTintList =
                    resources.getColorStateList(R.color.Gray_300)
            } else {
                btnWishUseMyFinish.isEnabled = true
                btnWishUseMyFinish.backgroundTintList =
                    resources.getColorStateList(R.color.Lightblue_500)
            }

            btnWishUseMyFinish.setOnClickListener {
                useWishDialog()
            }

            btnWishUseMyBack.setOnClickListener {
                activity?.finish()
            }

            val image = wishCouponImage
            ivWishUseMy.load(image)
        }
//        binding.tvWishUseMyDescription.text = wishFcViewModel.wishCouponContent.value
    }

    private fun useWishData() {
        wishFcViewModel.useWishData(wishCouponId)
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
                useWishData()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(parentFragmentManager, "")
    }
}
