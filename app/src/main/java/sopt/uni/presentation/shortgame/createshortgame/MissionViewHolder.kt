package sopt.uni.presentation.shortgame.createshortgame

import androidx.recyclerview.widget.RecyclerView
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.databinding.ItemMissionBinding

class MissionViewHolder(val binding: ItemMissionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        data: MissionDetail,
        selectMissionClickListener: (Int) -> Unit,
    ) {
        binding.apply {
            root.setOnClickListener {
                selectMissionClickListener(data.id)
            }
            missionDetail = data
        }
    }
}
