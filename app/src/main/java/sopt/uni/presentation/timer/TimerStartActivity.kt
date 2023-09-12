package sopt.uni.presentation.timer

import android.content.Context
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityTimerBinding
import sopt.uni.presentation.mypage.MypageSettingActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity


@AndroidEntryPoint
class TimerStartActivity : BindingActivity<ActivityTimerBinding>(R.layout.activity_timer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTimerBack.setOnSingleClickListener {
            startActivity<MypageSettingActivity>()
            finish()
        }

        // 이전에 설정한 타이머 상태가 있는지 확인하고 복원
        val sharedPreferences =
            applicationContext.getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val isActive = sharedPreferences.getBoolean("isTimerActive", false)
        val remainingTime = sharedPreferences.getLong("remainingSeconds", 0L)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (isActive) {
            // SharedPreferences에 타이머 상태가 활성화되어 있다면 TimerActiveFragment 실행
            sharedPreferences.edit().putBoolean("isTimerReActive", true).apply()
            val fragmentTimerActive =
                TimerActiveFragment(remainingTime)
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerActive)
        } else {
            // SharedPreferences에 타이머 상태가 비활성화되어 있다면 TimerSettingFragment 실행
            val fragmentTimerSetting = TimerSettingFragment()
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerSetting)
        }
        fragmentTransaction.commit()
    }
}
