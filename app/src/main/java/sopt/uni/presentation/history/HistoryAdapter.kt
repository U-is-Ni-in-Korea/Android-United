package sopt.uni.presentation.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.history.HistoryItem
import sopt.uni.databinding.ItemHistorylistBinding
import sopt.uni.util.extension.ItemDiffCallback
import sopt.uni.util.extension.setOnSingleClickListener

class HistoryAdapter(
    private val context: Context,
    private val onEmptyList: (Boolean) -> Unit,
    private val itemClickedListener: (HistoryItem) -> Unit,
) :
    ListAdapter<HistoryItem, HistoryAdapter.HistoryViewHolder>(
        ItemDiffCallback<HistoryItem>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    private val emptyStateObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            onEmptyList(currentList.isEmpty())
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            onEmptyList(currentList.isEmpty())
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            onEmptyList(currentList.isEmpty())
        }
    }

    init {
        registerAdapterDataObserver(emptyStateObserver)
        onEmptyList(currentList.isEmpty())
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistorylistBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: ItemHistorylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: HistoryItem) {
            with(binding) {
                historyItemDate.text = item.date
                historyItemImage.setImageDrawable(context.getDrawable(item.result_img))
                historyItemGameTitle.text = item.title
                historyItemGameResult.text = item.result
                historyItemRightImage.setImageDrawable(binding.root.context.getDrawable(item.next))

                historyItemRightImage.setOnSingleClickListener {
                    // 클릭 이벤트 처리
                    val item = getItem(adapterPosition)
                    itemClickedListener.invoke(item)
                }
            }
        }
    }
}
