package sopt.uni.presentation.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import sopt.uni.R
import sopt.uni.data.entity.history.HistoryList
import sopt.uni.databinding.ActivityMainHistoryBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.startActivity

class HistoryMainActivity :
    BindingActivity<ActivityMainHistoryBinding>(R.layout.activity_main_history) {

    private val historyAdapter by lazy {
        HistoryAdapter(
            context = this@HistoryMainActivity,
            onEmptyList = { isEmpty ->
                if (isEmpty) {
                    binding.itemHistoryEmptylist.visibility = View.VISIBLE
                } else {
                    binding.itemHistoryEmptylist.visibility = View.GONE
                }
            },
            itemClickedListener = { historyItem ->
                val intent = Intent(this, HistorySubActivity::class.java)
                startActivity(intent)
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        setupBackButton()
    }

    private fun setupRecyclerView() {
        binding.rvHistoryList.adapter = historyAdapter
        binding.rvHistoryList.layoutManager = LinearLayoutManager(this@HistoryMainActivity)

        historyAdapter.submitList(HistoryList)

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvHistoryList.context,
            LinearLayoutManager(this@HistoryMainActivity).orientation,
        )
        binding.rvHistoryList.addItemDecoration(dividerItemDecoration)
    }

    private fun setupBackButton() {
        binding.historyMainBack.setOnClickListener {
            startActivity<HomeActivity>()
            finish()
        }
    }
}
