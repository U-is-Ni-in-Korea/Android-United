package sopt.uni.presentation.timer

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.databinding.ActivityTimerBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class TimerStartActivity : BindingActivity<ActivityTimerBinding>(R.layout.activity_timer) {

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            exitTimer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBackButton()
        setTimerFragment()
        finishTimer()
    }

    private fun finishTimer() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun setTimerFragment() {
        val isActive = SparkleStorage.isActive
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (isActive) {
            val fragmentTimerActive =
                TimerActiveFragment(SparkleStorage.totalTime)
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerActive)
        } else {
            val fragmentTimerSetting = TimerSettingFragment()
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerSetting)
        }
        fragmentTransaction.commit()
    }

    private fun setBackButton() {
        binding.btnTimerBack.setOnSingleClickListener {
            exitTimer()
        }
    }

    private fun exitTimer() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_timer)

        if (currentFragment is TimerSettingFragment) {
            finish()
        } else {
            TimerDialogFragment().show(
                supportFragmentManager,
                TIMER_DIALOG_TAG,
            )
        }
    }

    companion object {
        private const val TIMER_DIALOG_TAG = "LeaveTimerDialogFragment"
    }
}
