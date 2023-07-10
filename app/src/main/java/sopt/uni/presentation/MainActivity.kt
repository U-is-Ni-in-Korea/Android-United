package sopt.uni.presentation

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMainBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        moveToPage()
    }

    private fun moveToPage() {
        binding.btnOnBoarding.setOnClickListener {
            startActivity<OnBoardingActivity>()
        }
    }
}
