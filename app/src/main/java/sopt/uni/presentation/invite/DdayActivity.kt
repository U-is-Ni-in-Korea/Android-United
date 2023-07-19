package sopt.uni.presentation.invite

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityDDayBinding
import sopt.uni.util.DateUtil
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity
import timber.log.Timber
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
    }

    private fun moveToShareInviteCode() {
        val formatedDate =
            binding.dpDDay.year.toString() + "-" + DateUtil.formatedMonth(binding.dpDDay.month) + "-" + DateUtil.formatedDay(
                binding.dpDDay.dayOfMonth,
            )
        binding.btnNext.setOnSingleClickListener {
            Timber.e(formatedDate)
            dDayViewModel.postStartDate(formatedDate)
            startActivity<ShareInviteCodeActivity>()
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }

    private fun setMaxDate() {
        val calendar = Calendar.getInstance()
        binding.dpDDay.maxDate = calendar.timeInMillis
    }
}
