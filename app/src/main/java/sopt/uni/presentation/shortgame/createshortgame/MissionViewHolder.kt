package sopt.uni.presentation.shortgame.createshortgame

import androidx.recyclerview.widget.RecyclerView
import coil.load
import sopt.uni.R
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.databinding.ItemMissionBinding
import sopt.uni.presentation.entity.MissionIdPosition

class MissionViewHolder(val binding: ItemMissionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        data: MissionDetail,
        isSelected: Boolean,
        position: Int,
        goToMissionDetailClickListener: (MissionIdPosition) -> Unit,
        selectMissionClickListener: (MissionIdPosition) -> Unit,
    ) {
        binding.apply {
            ivMission.load(data.image)
            tvMissionTitle.text = data.title
            ivMissionDetail.setOnClickListener {
                goToMissionDetailClickListener(MissionIdPosition(data.id, position))
            }
            root.setOnClickListener {
                selectMissionClickListener(MissionIdPosition(data.id, position))
            }
        }

        if (isSelected) {
            binding.clBackground.setBackgroundResource(R.drawable.bg_mission_hover)
        } else {
            binding.clBackground.setBackgroundResource(R.drawable.bg_white_round_10)
        }
    }
}
