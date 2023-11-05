package sopt.uni.presentation.shortgame.missionrecord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.uni.data.entity.shortgame.GameGuideItem
import sopt.uni.databinding.ItemGameGuideBinding
import sopt.uni.util.extension.ItemDiffCallback

class GameGuideViewPagerAdapter : ListAdapter<GameGuideItem, GameGuideViewHolder>(
    ItemDiffCallback<GameGuideItem>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): GameGuideViewHolder {
        val binding =
            ItemGameGuideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameGuideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameGuideViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
