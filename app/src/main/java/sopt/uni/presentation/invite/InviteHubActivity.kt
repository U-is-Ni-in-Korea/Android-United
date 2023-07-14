package sopt.uni.presentation.invite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityInviteHubBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class InviteHubActivity : BindingActivity<ActivityInviteHubBinding>(R.layout.activity_invite_hub) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moveToEnterDday()
        moveToEnterInviteCode()
        moveToPrevPage()
    }

    private fun moveToEnterDday() {
        binding.btnSendInviteCod.setOnSingleClickListener() {
            startActivity<DdayActivity>()
        }
    }

    private fun moveToEnterInviteCode() {
        binding.btnEnterInviteCode.setOnSingleClickListener() {
            startActivity<EnterInviteCodeActivity>()
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }
}
