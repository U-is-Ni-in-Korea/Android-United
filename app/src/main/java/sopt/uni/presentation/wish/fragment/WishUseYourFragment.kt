package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.databinding.FragmentWishUseYourBinding
import sopt.uni.presentation.wish.WishFcViewModel
@AndroidEntryPoint
class WishUseYourFragment : Fragment() {
    private var _binding: FragmentWishUseYourBinding? = null
    private val binding: FragmentWishUseYourBinding
        get() = requireNotNull(_binding) { "binding is null" }
    private var wishCouponId: Int = 0
    private val wishFcViewModel: WishFcViewModel by viewModels()

    companion object {
        fun newInstance(wishCouponId: Int): WishUseYourFragment {
            val fragment = WishUseYourFragment()
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
        _binding = FragmentWishUseYourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWishUseYourBack.setOnClickListener {
            activity?.finish()
        }

        val bundle = arguments
        if (bundle != null) {
            wishCouponId = bundle.getInt("wishCouponId")
        }
        Log.d("wishCouponId", "$wishCouponId")

        wishFcViewModel.setWishDetailData(wishCouponId)
    }
}
