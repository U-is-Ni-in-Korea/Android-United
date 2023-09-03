package sopt.uni.presentation.timer

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityTimerBinding
import sopt.uni.util.binding.BindingActivity

@AndroidEntryPoint
class TimerStartActivity : BindingActivity<ActivityTimerBinding>(R.layout.activity_timer) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSetting()
    }

    private fun initSetting() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragmentTimerSetting = TimerSettingFragment()
        fragmentTransaction.add(R.id.fcv_timer, fragmentTimerSetting)
        fragmentTransaction.commit()
    }

}