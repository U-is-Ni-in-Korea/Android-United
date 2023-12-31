package sopt.uni.presentation.history

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.entity.history.History
import sopt.uni.databinding.ActivityHistoryDetailBinding
import sopt.uni.presentation.history.HistoryActivity.Companion.HISTORY
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class HistoryDetailActivity :
    BindingActivity<ActivityHistoryDetailBinding>(R.layout.activity_history_detail) {
    private val viewModel: HistoryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel
        setHistory()
        setupBackButton()
    }

    private fun setHistory() {
        val history = intent.parcelable<History>(HISTORY)
        viewModel.setHistory(requireNotNull(history))
    }

    private fun setupBackButton() {
        binding.btnHistoryDetailBack.setOnSingleClickListener {
            finish()
        }
    }
}
