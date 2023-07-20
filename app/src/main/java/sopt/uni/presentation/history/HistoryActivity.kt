package sopt.uni.presentation.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityHistoryBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class HistoryActivity :
    BindingActivity<ActivityHistoryBinding>(R.layout.activity_history) {

    private val viewModel: HistoryViewModel by viewModels()

    private val historyAdapter by lazy {
        HistoryAdapter(
            onClick = { position ->
                val intent = Intent(this, HistoryDetailActivity::class.java)
                intent.putExtra(
                    HISTORY,
                    requireNotNull(viewModel.historyData.value).toList()[position],
                )
                startActivity(intent)
            },
            setResult = { position ->
                when (requireNotNull(viewModel.historyData.value).toList()[position].result) {
                    WIN -> {
                        getString(R.string.history_win)
                    }

                    LOSE -> {
                        getString(R.string.history_lose)
                    }

                    else -> {
                        getString(R.string.history_draw)
                    }
                }
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel
        initHistoryAdapter()
        setupBackButton()

        viewModel.historyData.observe(this) { historyList ->
            historyAdapter.submitList(historyList)
        }
    }

    private fun initHistoryAdapter() {
        binding.rvHistory.adapter = historyAdapter

        historyAdapter.submitList(viewModel.historyData.value)

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvHistory.context,
            LinearLayoutManager(this@HistoryActivity).orientation,
        )
        binding.rvHistory.addItemDecoration(dividerItemDecoration)
    }

    private fun setupBackButton() {
        binding.btnHistoryBack.setOnSingleClickListener {
            startActivity<HomeActivity>()
            finishAffinity()
        }
    }

    companion object {
        const val HISTORY = "history"
        private const val WIN = "WIN"
        private const val LOSE = "LOSE"
    }
}
