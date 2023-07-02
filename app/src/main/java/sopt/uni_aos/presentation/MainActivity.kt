package sopt.uni_aos.presentation

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni_aos.R
import sopt.uni_aos.util.binding.BindingActivity
import sopt.uni_aos.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
