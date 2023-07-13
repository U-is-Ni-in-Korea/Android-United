package sopt.uni.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import sopt.uni.R
import sopt.uni.data.entity.history.HistoryList
import sopt.uni.databinding.ActivityMainHistoryBinding
import sopt.uni.util.binding.BindingActivity

class MainHistoryActivity :
    BindingActivity<ActivityMainHistoryBinding>(R.layout.activity_main_history) {

    private val historyAdapter by lazy {
        HistoryAdapter(
            context = this@MainHistoryActivity,
            onEmptyList = { isEmpty ->
                if (isEmpty) {
                    binding.itemHistoryEmptylist.visibility = View.VISIBLE
                } else {
                    binding.itemHistoryEmptylist.visibility = View.GONE
                }
            },
            itemClickedListener = { historyItem ->
                val intent = Intent(this, SubHistoryActivity::class.java)
                startActivity(intent)
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvHistoryList.adapter = historyAdapter
        binding.rvHistoryList.layoutManager = LinearLayoutManager(this@MainHistoryActivity)

        historyAdapter.submitList(HistoryList)

        val dividerItemDecoration =
            DividerItemDecoration(
                binding.rvHistoryList.context,
                LinearLayoutManager(this@MainHistoryActivity).orientation,
            )
        binding.rvHistoryList.addItemDecoration(dividerItemDecoration)

        binding.historyMainBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}