package sopt.uni.presentation.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityHistoryBinding
import sopt.uni.util.UiState
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class HistoryActivity : BindingActivity<ActivityHistoryBinding>(R.layout.activity_history) {

    private val viewModel: HistoryViewModel by viewModels()

    private val historyAdapter by lazy {
        HistoryAdapter(
            onClick = { position ->
                val intent = Intent(this, HistoryDetailActivity::class.java)
                val historyList = when (val uiState = viewModel.historyData.value) {
                    is UiState.Success -> uiState.data
                    else -> emptyList()
                }
                intent.putExtra(HISTORY, historyList[position])
                startActivity(intent)
            },
            setResult = { position ->
                val historyList = when (val uiState = viewModel.historyData.value) {
                    is UiState.Success -> uiState.data
                    else -> emptyList()
                }
                val result = when (historyList[position].result) {
                    WIN -> getString(R.string.history_win)
                    LOSE -> getString(R.string.history_lose)
                    else -> getString(R.string.history_draw)
                }
                result
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel
        initHistoryAdapter()
        observeHistoryData()
        setupBackButton()
    }

    private fun initHistoryAdapter() {
        binding.rvHistory.adapter = historyAdapter
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvHistory.context,
            LinearLayoutManager(this@HistoryActivity).orientation,
        )
        binding.rvHistory.addItemDecoration(dividerItemDecoration)
    }

    private fun observeHistoryData() {
        viewModel.historyData.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.sflHistory.stopShimmer()
                    val historylist = uiState.data
                    historyAdapter.submitList(historylist)
                }

                is UiState.Loading -> {
                    binding.sflHistory.startShimmer()
                }

                else -> {}
            }
        }
    }

    private fun setupBackButton() {
        binding.btnHistoryBack.setOnSingleClickListener {
            finish()
        }
    }

    companion object {
        const val HISTORY = "history"
        private const val WIN = "WIN"
        private const val LOSE = "LOSE"
    }
}
