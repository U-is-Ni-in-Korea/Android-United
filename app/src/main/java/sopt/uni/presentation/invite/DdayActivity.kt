package sopt.uni.presentation.invite

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.databinding.ActivityDDayBinding
import sopt.uni.presentation.common.content.INVITECODE
import sopt.uni.util.DateUtil
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity
import java.util.Calendar

@AndroidEntryPoint
class DdayActivity : BindingActivity<ActivityDDayBinding>(R.layout.activity_d_day) {
    private val dDayViewModel by viewModels<DdayViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMaxDate()
        moveToShareInviteCode()
        moveToPrevPage()
        initOnBackPressedListener()
    }

    private fun moveToShareInviteCode() {
        binding.btnNext.setOnSingleClickListener {
            val formatedDate =
                binding.dpDDay.year.toString() + "-" + DateUtil.formatedMonth(binding.dpDDay.month + 1) + "-" + DateUtil.formatedDay(
                    binding.dpDDay.dayOfMonth,
                )
            dDayViewModel.postStartDate(formatedDate)
            lifecycleScope.launch {
                dDayViewModel.inviteCode.collect { inviteCode ->
                    if (inviteCode.isNotEmpty()) {
                        Intent(this@DdayActivity, ShareInviteCodeActivity::class.java).apply {
                            putExtra(INVITECODE, inviteCode)
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            backToInviteHub()
        }
    }

    private fun setMaxDate() {
        val calendar = Calendar.getInstance()
        binding.dpDDay.maxDate = calendar.timeInMillis
    }

    private fun initOnBackPressedListener() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToInviteHub()
            }
        }
        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun backToInviteHub() {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity<InviteHubActivity>()
        finish()
    }
}
