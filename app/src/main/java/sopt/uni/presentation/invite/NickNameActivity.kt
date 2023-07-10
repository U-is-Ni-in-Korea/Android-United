package sopt.uni.presentation.invite

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivityNicknameBinding
import sopt.uni.util.binding.BindingActivity

class NickNameActivity : BindingActivity<ActivityNicknameBinding>(R.layout.activity_nickname) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
