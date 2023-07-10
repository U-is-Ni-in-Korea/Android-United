package sopt.uni.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import sopt.uni.R
import sopt.uni.databinding.ActivityMainHistoryBinding
import sopt.uni.util.binding.BindingActivity

class MainHistoryActivity :
    BindingActivity<ActivityMainHistoryBinding>(R.layout.activity_main_history) {

    private val viewModel by viewModels<HistoryViewModel>()
    private lateinit var historyAdapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        historyAdapter = HistoryAdapter(this)
        binding.rvHistoryList.adapter = historyAdapter
        binding.rvHistoryList.layoutManager = LinearLayoutManager(this)
        val mockRepoList = viewModel.getMockRepoList()
        historyAdapter.submitList(mockRepoList)

        val dividerItemDecoration =
            DividerItemDecoration(binding.rvHistoryList.context, LinearLayoutManager(this).orientation)

        binding.rvHistoryList.addItemDecoration(dividerItemDecoration)
    }
}
