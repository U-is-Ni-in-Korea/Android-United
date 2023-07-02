package sopt.uni_aos.presentation

import android.os.Bundle
import sopt.uni_aos.R
import sopt.uni_aos.base.BindingActivity
import sopt.uni_aos.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
