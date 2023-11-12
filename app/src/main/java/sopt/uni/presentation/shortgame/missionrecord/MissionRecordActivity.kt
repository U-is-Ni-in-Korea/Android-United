package sopt.uni.presentation.shortgame.missionrecord

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.entity.shortgame.GameGuideItem
import sopt.uni.data.entity.shortgame.MissionToolState
import sopt.uni.databinding.ActivityMissionRecordBinding
import sopt.uni.presentation.shortgame.createshortgame.dialog.CreateShortGameDialogFragment
import sopt.uni.presentation.shortgame.missiondetailrecord.MissionDetailRecordActivity
import sopt.uni.presentation.memo.MemoActivity
import sopt.uni.presentation.shortgame.missionresult.MissionResultActivity
import sopt.uni.presentation.shortgame.missionresult.MissionResultViewModel
import sopt.uni.presentation.shortgame.missionresult.MissionWaitingResultActivity
import sopt.uni.presentation.timer.TimerStartActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class MissionRecordActivity :
    BindingActivity<ActivityMissionRecordBinding>(R.layout.activity_mission_record) {

    private val viewModel: MissionRecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.missionRecordViewModel = viewModel
        setViewModelObserve()
        setClickListener()
    }

    private fun cancelDialog() {
        CreateShortGameDialogFragment().apply {
            titleText =
                this@MissionRecordActivity.resources.getString(R.string.mission_detatil_record_rule_title)
            bodyText =
                this@MissionRecordActivity.resources.getString(R.string.mission_record_cancel_dialog_body)
            confirmButtonText =
                this@MissionRecordActivity.resources.getString(R.string.dialog_ok_text)
            dismissButtonText =
                this@MissionRecordActivity.resources.getString(R.string.dialog_cancel_text)
            confirmClickListener = {
                viewModel.requestStopMission()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(supportFragmentManager, "")
    }

    private fun setViewModelObserve() {
        viewModel.isMissionDeleteSuccess.observe(this) {
            if (it) finish()
        }

        viewModel.isMissionRequestSuccess.observe(this) {
            if (it) {
                MissionWaitingResultActivity.start(this, viewModel.roundGameId)
                finish()
            }
        }
        viewModel.myMissionResult.observe(this) {
            val ruleDescription = it.missionContent.missionCategory.rule
            val tipDescription = it.missionContent.missionCategory.tip
            val gameGuideList = listOf<GameGuideItem>(
                GameGuideItem(
                    title = getString(R.string.mission_record_rule),
                    description = ruleDescription,
                ),
                GameGuideItem(
                    title = getString(R.string.mission_record_tip),
                    description = tipDescription,
                ),
            )
            setAdapter(gameGuideList)
        }
        viewModel.missionToolState.observe(this) {
            when (it) {
                MissionToolState.MEMO ->
                    binding.clCardMemo.visibility = View.VISIBLE

                MissionToolState.TIMER ->
                    binding.clCardTimer.visibility = View.VISIBLE

                else -> {}
            }
        }
    }

    private fun setAdapter(list: List<GameGuideItem>) {
        binding.vpGameGuide.adapter = GameGuideViewPagerAdapter().apply {
            submitList(list)
        }
        binding.indicator.setViewPager2(binding.vpGameGuide)
    }

    private fun setClickListener() {
        binding.apply {
            ivClose.setOnSingleClickListener {
                finish()
            }
            btnMissionComplete.setOnClickListener {
                viewModel.requestMission(true)
            }
            btnMissionFail.setOnSingleClickListener {
                viewModel.requestMission(false)
            }
            clCardTimer.setOnSingleClickListener {
                startActivity<TimerStartActivity>()
            }
            clCardMemo.setOnSingleClickListener {
                startActivity<MemoActivity>()
            }
        }
    }

    companion object {
        const val ROUND_GAME_ID = "ROUND_GAME_ID"

        fun start(context: Context, roundGameId: Int) {
            context.startActivity(getIntent(context, roundGameId))
        }

        fun getIntent(context: Context, roundGameId: Int) =
            Intent(context, MissionRecordActivity::class.java).putExtra(ROUND_GAME_ID, roundGameId)
    }
}
