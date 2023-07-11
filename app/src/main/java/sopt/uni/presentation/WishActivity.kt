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
                tvWishMyWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishMyWish.setTextAppearance(R.style.Subtitle)
                tvWishYourWish.setTextAppearance(R.style.Body1_Regular)
                changeFragment(WishMyWishFragment())
            }
            tvWishYourWish.setOnClickListener() {
                tvWishMyWish.setTextColor(resources.getColor(R.color.Gray_300))
                tvWishYourWish.setTextColor(resources.getColor(R.color.Lightblue_600))
                tvWishMyWish.setTextAppearance(R.style.Body1_Regular)
                tvWishYourWish.setTextAppearance(R.style.Subtitle)
                changeFragment(WishYourWishFragment())
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_wish, fragment).commit()
    }
}
