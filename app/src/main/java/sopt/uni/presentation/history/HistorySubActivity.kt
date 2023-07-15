package sopt.uni.presentation.history

import android.content.Intent
import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivitySubHistoryBinding
import sopt.uni.util.binding.BindingActivity

class HistorySubActivity :
    BindingActivity<ActivitySubHistoryBinding>(R.layout.activity_sub_history) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.historySubBack.setOnClickListener {
            val intent = Intent(this, HistoryMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
