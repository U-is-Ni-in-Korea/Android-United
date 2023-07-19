package sopt.uni.presentation.shortgame.createshortgame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.databinding.ItemMissionBinding
import sopt.uni.presentation.entity.MissionIdPosition
import sopt.uni.util.extension.ItemDiffCallback

class MissionCategoryAdapter(
    private val goToMissionDetailClickListener: (MissionIdPosition) -> Unit,
    private val selectMissionClickListener: (MissionIdPosition) -> Unit,
) : ListAdapter<MissionDetail, MissionViewHolder>(
    ItemDiffCallback<MissionDetail>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {
    private var selectedPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        val binding =
            ItemMissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MissionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        holder.onBind(
            getItem(position),
            position == selectedPosition,
            position,
            goToMissionDetailClickListener,
            selectMissionClickListener,
        )
    }

    fun setSelectedItem(position: Int) {
        val prevPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(prevPosition)
        notifyItemChanged(selectedPosition)
    }
}
