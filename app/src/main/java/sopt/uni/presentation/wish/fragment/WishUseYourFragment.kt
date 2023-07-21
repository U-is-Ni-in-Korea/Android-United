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
import sopt.uni.databinding.FragmentWishUseYourBinding
import sopt.uni.presentation.wish.WishActivity
import sopt.uni.presentation.wish.WishFcViewModel

@AndroidEntryPoint
class WishUseYourFragment : Fragment() {
    private var _binding: FragmentWishUseYourBinding? = null
    private val binding: FragmentWishUseYourBinding
        get() = requireNotNull(_binding) { "binding is null" }
    private val wishFcViewModel: WishFcViewModel by viewModels()
    private var wishCouponId: Int = 0
    private var wishCouponImage: String = ""
    private var wishCouponIsUsed: Boolean = true

    companion object {
        fun newInstance(wishCouponId: WishActivity.WishTypeId): WishUseYourFragment {
            val fragment = WishUseYourFragment()
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
        _binding = FragmentWishUseYourBinding.inflate(inflater, container, false)
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

        binding.btnWishUseYourBack.setOnClickListener {
            activity?.finish()
        }

        wishFcViewModel.setWishDetailData(wishCouponId)

        val image = wishCouponImage
        binding.ivWishUseYour.load(image)

        with(binding) {

            if (wishCouponIsUsed == true) {
                tvWishUseYourDescription.text =
                    this@WishUseYourFragment.resources.getString(R.string.wish_your)
            } else {
                tvWishUseYourDescription.text =
                    this@WishUseYourFragment.resources.getString(R.string.wish_your_used)
            }
        }
    }
}
