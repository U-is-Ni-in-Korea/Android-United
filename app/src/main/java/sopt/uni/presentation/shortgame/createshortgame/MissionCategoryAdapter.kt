package sopt.uni.presentation.shortgame.createshortgame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.databinding.ItemMissionBinding
import sopt.uni.util.extension.ItemDiffCallback

class MissionCategoryAdapter(
    private val selectMissionClickListener: (Int) -> Unit,
) : ListAdapter<MissionDetail, MissionViewHolder>(
    ItemDiffCallback<MissionDetail>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        val binding =
            ItemMissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MissionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        holder.onBind(
            getItem(position),
            selectMissionClickListener,
        )
    }
}
