package sopt.uni.presentation.invite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityShareInviteCodeBinding
import sopt.uni.presentation.common.content.INVITECODE
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import timber.log.Timber

@AndroidEntryPoint
class ShareInviteCodeActivity :
    BindingActivity<ActivityShareInviteCodeBinding>(R.layout.activity_share_invite_code) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moveToPrevPage()
        getInviteCode()
    }

    private fun getInviteCode() {
        Timber.e(intent.getStringExtra(INVITECODE))
        binding.tvInviteCode.text = intent.getStringExtra(INVITECODE)
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }
}
