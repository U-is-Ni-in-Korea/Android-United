package sopt.uni.presentation.invite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityDDayBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class DdayActivity : BindingActivity<ActivityDDayBinding>(R.layout.activity_d_day) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moveToShareInviteCode()
        moveToPrevPage()
    }

    private fun moveToShareInviteCode() {
        binding.btnNext.setOnSingleClickListener {
            startActivity<ShareInviteCodeActivity>()
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }
}
