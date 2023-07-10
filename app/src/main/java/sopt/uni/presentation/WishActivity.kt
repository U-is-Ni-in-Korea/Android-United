package sopt.uni.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import sopt.uni.R
import sopt.uni.databinding.ActivityWishBinding

class WishActivity : AppCompatActivity() {
    lateinit var binding: ActivityWishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_wish)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_wish, WishMyWishFragment()).commit()
        }

        with(binding) {
            tvWishMyWish.setOnClickListener() {
                changeFragment(WishMyWishFragment())
            }
            tvWishYourWish.setOnClickListener() {
                changeFragment(WishYourWishFragment())
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_wish, fragment).commit()
    }
}
