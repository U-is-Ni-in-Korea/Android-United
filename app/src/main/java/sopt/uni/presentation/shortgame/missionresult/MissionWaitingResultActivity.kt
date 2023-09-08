package sopt.uni.presentation.shortgame.missionresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMissionWaitingResultBinding
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class MissionWaitingResultActivity :
    BindingActivity<ActivityMissionWaitingResultBinding>(R.layout.activity_mission_waiting_result) {
    private val viewModel: MissionResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.missionResultViewModel = viewModel
        initViewModelObserve()
        closeClickListener()
    }

    private fun initViewModelObserve() {
        viewModel.apply {
            partnerMissionResult.observe(this@MissionWaitingResultActivity) {
                if (it == null) {
                    goFinalClickListener(true)
                } else {
                    goFinalClickListener(false)
                }
            }
        }
    }

    private fun goFinalClickListener(isEmpty: Boolean) {
        binding.btnGoFinalResult.setOnClickListener {
            if (isEmpty) {
                showSnackbar(binding.root, getString(R.string.mission_waiting_partner_result))
            } else {
                startActivity<MissionResultActivity>()
                finish()
            }
        }
    }

    private fun closeClickListener() {
        binding.ivClose.setOnSingleClickListener { finish() }
    }

    companion object {

        fun start(context: Context, roundGameId: Int) {
            context.startActivity(getIntent(context, roundGameId))
        }

        private fun getIntent(context: Context, roundGameId: Int) =
            Intent(context, MissionWaitingResultActivity::class.java).putExtra(
                MissionRecordActivity.ROUND_GAME_ID,
                roundGameId,
            )

        @JvmStatic
        @BindingAdapter("setDateResult", "setMissionComplete")
        fun setDateResult(view: TextView, date: String?, isMissionComplete: String?) {
            if (date == null || isMissionComplete == "LOSE") {
                view.text = view.context.getString(R.string.mission_result_failure)
                view.background = view.context.getDrawable(R.drawable.wish_ment_pink_rectangle)
            } else {
                val dateString = date.split("T")[1].substring(0, 5)
                view.text =
                    "$dateString ${view.context.getString(R.string.mission_result_complete)}"
                view.background = view.context.getDrawable(R.drawable.wish_ment_green_rectangle)
            }
        }
    }
}
