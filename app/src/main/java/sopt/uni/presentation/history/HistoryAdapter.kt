package sopt.uni.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.history.History
import sopt.uni.databinding.ItemHistoryBinding
import sopt.uni.util.extension.ItemDiffCallback
import sopt.uni.util.extension.setOnSingleClickListener

class HistoryAdapter(
    private val onClick: (Int) -> Unit,
    private val setResult: (Int) -> String,
) : ListAdapter<History, HistoryAdapter.HistoryViewHolder>(
    ItemDiffCallback<History>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new },
    ),
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: History) {
            binding.data = data
            binding.tvItemHistoryResult.text = setResult(absoluteAdapterPosition)
            binding.btnHistoryItemRight.setOnSingleClickListener {
                onClick(absoluteAdapterPosition)
            }
        }
    }
}
