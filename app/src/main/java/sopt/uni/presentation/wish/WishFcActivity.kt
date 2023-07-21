package sopt.uni.presentation.wish

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityWishFcBinding
import sopt.uni.presentation.wish.fragment.WishNewWishFragment
import sopt.uni.presentation.wish.fragment.WishUseMyFragment
import sopt.uni.presentation.wish.fragment.WishUseYourFragment
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable

@AndroidEntryPoint
class WishFcActivity : BindingActivity<ActivityWishFcBinding>(R.layout.activity_wish_fc) {
    private val wishFcViewModel: WishFcViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = wishFcViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val wishCouponId = intent.parcelable<WishActivity.WishTypeId>(WishActivity.WISH_TYPE_ID)

        wishFcViewModel.setWishDetailData(wishCouponId = wishCouponId!!.id)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (wishCouponId.type == 0) {
            fragmentTransaction.replace(R.id.fcv_wish_fc, WishNewWishFragment()).commit()
        } else {
            if (wishCouponId.isMine) {
                val myFragment = WishUseMyFragment.newInstance(wishCouponId)
                fragmentTransaction.replace(R.id.fcv_wish_fc, myFragment).commit()
            } else {
                val YourFragment = WishUseYourFragment.newInstance(wishCouponId)
                fragmentTransaction.replace(R.id.fcv_wish_fc, YourFragment).commit()
            }
        }
    }
}
