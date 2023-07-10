package sopt.uni.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.uni.databinding.ItemHistorylistBinding
import sopt.uni.util.extension.ItemDiffCallback

class HistoryAdapter(private val context: Context) :
    ListAdapter<HistoryViewModel.Repo, HistoryAdapter.HistoryViewHolder>(
        ItemDiffCallback<HistoryViewModel.Repo>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistorylistBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class HistoryViewHolder(private val binding: ItemHistorylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: HistoryViewModel.Repo) {
            binding.tvDate.text = item.date
            binding.ivResult.setImageDrawable(binding.root.context.getDrawable(item.result_img))
            binding.tvGame.text = item.title
            binding.tvResult.text = item.result
            binding.ivNext.setImageDrawable(binding.root.context.getDrawable(item.next))
        }
    }
}
