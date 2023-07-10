package sopt.uni.presentation.onboarding

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.entity.onboarding.onBoardingList
import sopt.uni.databinding.ActivityOnBoardingBinding
import sopt.uni.util.binding.BindingActivity

@AndroidEntryPoint
class OnBoardingActivity :
    BindingActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdaptor()
    }

    private fun setAdaptor() {
        binding.vpOnBoarding.adapter = OnBoardingAdaptor().apply {
            submitList(onBoardingList)
        }

        binding.indicator.setViewPager2(binding.vpOnBoarding)
    }
}
