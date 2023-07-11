package sopt.uni.presentation.invite

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivityEnterInviteCodeBinding
import sopt.uni.util.binding.BindingActivity

class EnterInviteCodeActivity :
    BindingActivity<ActivityEnterInviteCodeBinding>(R.layout.activity_enter_invite_code) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
