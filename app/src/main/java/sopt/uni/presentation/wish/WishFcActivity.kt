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

@AndroidEntryPoint
class WishFcActivity : BindingActivity<ActivityWishFcBinding>(R.layout.activity_wish_fc) {
    private val wishFcViewModel: WishFcViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = wishFcViewModel
        setContentView(binding.root)

        val wishCouponId = intent.getIntExtra("wishCouponId", -1)

        wishFcViewModel.setWishDetailData(wishCouponId)
        Log.d("wishCouponsub", "${wishFcViewModel.wishCouponContent.value}")
        Log.d("wishCouponId_test", "$wishCouponId")

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_wish_fc)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

//        if (currentFragment == null) {
//            if (wishFcViewModel.isMine.value == true) {
//                val fragment = WishUseMyFragment.newInstance(wishCouponId)
//                fragmentTransaction.replace(R.id.fcv_wish_fc, fragment).commit()
//            } else {
//                val fragment = WishUseYourFragment.newInstance(wishCouponId)
//                fragmentTransaction.replace(R.id.fcv_wish_fc, fragment).commit()
//            }
//        } else {
//            if (wishFcViewModel.isMine.value == true) {
//                val fragment = WishUseMyFragment.newInstance(wishCouponId)
//                fragmentTransaction.replace(R.id.fcv_wish_fc, fragment).commit()
//            } else {
//                val fragment = WishUseYourFragment.newInstance(wishCouponId)
//                fragmentTransaction.replace(R.id.fcv_wish_fc, fragment).commit()
//            }
//        }


        if (wishCouponId == -1) {
            fragmentTransaction.replace(R.id.fcv_wish_fc, WishNewWishFragment()).commit()
        } else {
            if (wishFcViewModel.isMine.value == true) {
                val fragment = WishUseMyFragment.newInstance(wishCouponId)
                fragmentTransaction.replace(R.id.fcv_wish_fc, fragment).commit()
            } else {
                val fragment = WishUseYourFragment.newInstance(wishCouponId)
                fragmentTransaction.replace(R.id.fcv_wish_fc, fragment).commit()
            }
        }
    }
}
