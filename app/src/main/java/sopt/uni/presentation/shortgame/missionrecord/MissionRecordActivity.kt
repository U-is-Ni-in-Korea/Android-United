package sopt.uni.presentation.shortgame.missionrecord

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMissionRecordBinding
import sopt.uni.presentation.shortgame.missiondetailrecord.MissionDetailRecordActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class MissionRecordActivity :
    BindingActivity<ActivityMissionRecordBinding>(R.layout.activity_mission_record) {

    private val viewModel: MissionRecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.missionRecordViewModel = viewModel
        setClickListener()
    }

    private fun setClickListener() {
        binding.apply {
            tvStopGame.setOnSingleClickListener {
                // TODO : 승부 그만두기 다이얼로그
            }
            ivMissionDetail.setOnSingleClickListener {
                MissionDetailRecordActivity.start(
                    this@MissionRecordActivity,
                    viewModel.missionId.value,
                )
            }
            ivClose.setOnSingleClickListener {
                finish()
            }
            btnMissionComplete.setOnClickListener {
                viewModel.requestMission(true)
            }
            btnMissionFail.setOnSingleClickListener {
                viewModel.requestMission(false)
            }
        }
    }

    companion object {
        const val ROUND_GAME_ID = "ROUND_GAME_ID"

        fun start(context: Context, roundGameId: Int) {
            context.startActivity(getIntent(context, roundGameId))
        }

        private fun getIntent(context: Context, roundGameId: Int) = Intent(context, MissionRecordActivity::class.java).putExtra(ROUND_GAME_ID, roundGameId)
    }
}
