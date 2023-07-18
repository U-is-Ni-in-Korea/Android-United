package sopt.uni.presentation.wish

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivityWishFcBinding
import sopt.uni.presentation.wish.fragment.WishUseMyFragment
import sopt.uni.util.binding.BindingActivity

class WishFcActivity : BindingActivity<ActivityWishFcBinding>(R.layout.activity_wish_fc) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_wish_fc)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_wish_fc, WishUseMyFragment()).commit()
        }
    }
}
