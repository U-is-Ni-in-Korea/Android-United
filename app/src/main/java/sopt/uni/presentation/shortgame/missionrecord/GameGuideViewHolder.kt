package sopt.uni.presentation.shortgame.missionrecord

import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.shortgame.GameGuideItem
import sopt.uni.databinding.ItemGameGuideBinding

class GameGuideViewHolder(val binding: ItemGameGuideBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: GameGuideItem) {
        binding.tvTitle.text = data.title
        binding.tvDescription.text = data.description
    }
}
