package sopt.uni.presentation.invite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityNicknameBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class NickNameActivity : BindingActivity<ActivityNicknameBinding>(R.layout.activity_nickname) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moveToInviteHub()
        moveToPrevPage()
    }

    private fun moveToInviteHub() {
        binding.btnNext.setOnSingleClickListener() {
            startActivity<InviteHubActivity>()
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }
}
