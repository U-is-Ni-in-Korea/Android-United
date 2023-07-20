package sopt.uni.presentation.onboarding

import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.entity.onboarding.onBoardingList
import sopt.uni.databinding.ActivityOnBoardingBinding
import sopt.uni.presentation.login.LoginActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class OnBoardingActivity :
    BindingActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdaptor()
        changeSkipText()
        moveToLogin()
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                "한번 더 뒤로가기 버튼을 누르면 종료됩니다.",
                Toast.LENGTH_SHORT,
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun setAdaptor() {
        binding.vpOnBoarding.adapter = OnBoardingAdaptor().apply {
            submitList(onBoardingList)
        }

        binding.indicator.setViewPager2(binding.vpOnBoarding)
    }

    private fun changeSkipText() {
        binding.vpOnBoarding.registerOnPageChangeCallback(
            object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == 2) {
                        binding.tvSkip.text = getString(R.string.on_boarding_start)
                    } else {
                        binding.tvSkip.text = getString(R.string.on_boarding_skip)
                    }
                }
            },
        )
    }

    private fun moveToLogin() {
        binding.tvSkip.setOnSingleClickListener {
            startActivity<LoginActivity>()
            finishAffinity()
        }
    }
}
