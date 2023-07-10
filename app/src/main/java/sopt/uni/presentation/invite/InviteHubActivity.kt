package sopt.uni.presentation.invite

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivityInviteHubBinding
import sopt.uni.util.binding.BindingActivity

class InviteHubActivity : BindingActivity<ActivityInviteHubBinding>(R.layout.activity_invite_hub) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
