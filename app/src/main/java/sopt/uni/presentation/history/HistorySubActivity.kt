package sopt.uni.presentation.history

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivitySubHistoryBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

class HistorySubActivity :
    BindingActivity<ActivitySubHistoryBinding>(R.layout.activity_sub_history) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.historySubBack.setOnSingleClickListener {
            startActivity<HistoryMainActivity>()
            finish()
        }
    }
}
