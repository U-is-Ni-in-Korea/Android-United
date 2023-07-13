package sopt.uni.presentation.invite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityShareInviteCodeBinding
import sopt.uni.util.binding.BindingActivity

@AndroidEntryPoint
class ShareInviteCodeActivity :
    BindingActivity<ActivityShareInviteCodeBinding>(R.layout.activity_share_invite_code) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
