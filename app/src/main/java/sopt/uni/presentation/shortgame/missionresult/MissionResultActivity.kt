package sopt.uni.presentation.shortgame.missionresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import sopt.uni.R
import sopt.uni.data.entity.shortgame.MissionResultState
import sopt.uni.databinding.ActivityMissionResultBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

class MissionResultActivity :
    BindingActivity<ActivityMissionResultBinding>(R.layout.activity_mission_result) {

    private val viewModel: MissionResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.missionResultViewModel = viewModel
        initViewModelObserve()
        initClickListener()
    }

    private fun initViewModelObserve() {
        viewModel.apply {
            myMissionResultState.observe(this@MissionResultActivity) {
                setResultImageText(it)
                setButtonVisible(it)
            }
            partnerMissionResult.observe(this@MissionResultActivity) {
                setPartnerMissionContent(it == null)
            }
        }
    }

    private fun setResultImageText(state: MissionResultState) {
        val imageArray = this.resources.obtainTypedArray(R.array.result_state_image)
        val stringArray = this.resources.obtainTypedArray(R.array.result_state_text)
        binding.ivMissionResult.setImageResource(imageArray.getResourceId(state.order, 0))
        binding.tvMyMissionResult.text = stringArray.getString(state.order)
    }

    private fun setButtonVisible(state: MissionResultState) {
        when (state) {
            MissionResultState.WIN -> binding.btnGoWish.visibility = View.VISIBLE

            else -> binding.btnGoHome.visibility = View.VISIBLE
        }
    }

    private fun setPartnerMissionContent(isEmpty: Boolean) {
        if (isEmpty) {
            binding.clCardPartnerMission.visibility = View.INVISIBLE
            binding.clCardPartnerMissionEmpty.visibility = View.VISIBLE
        } else {
            binding.clCardPartnerMission.visibility = View.VISIBLE
            binding.clCardPartnerMissionEmpty.visibility = View.INVISIBLE
        }
    }

    private fun initClickListener() {
        binding.apply {
            ivClose.setOnSingleClickListener { finish() }
            btnGoHome.setOnSingleClickListener { finish() }
            btnGoWish.setOnClickListener { // TODO : 소원권 페이지 이동 }
            }
        }
    }

    companion object {
        const val ROUND_GAME_ID = "ROUND_GAME_ID"

        fun start(context: Context, roundGameId: Int) {
            context.startActivity(getIntent(context, roundGameId))
        }

        private fun getIntent(context: Context, roundGameId: Int) =
            Intent(context, MissionResultActivity::class.java).putExtra(
                ROUND_GAME_ID,
                roundGameId,
            )

        @JvmStatic
        @BindingAdapter("setDateResult")
        fun setDateResult(view: TextView, date: String?) {
            if (date == null) {
                view.text = view.context.getString(R.string.mission_result_failure)
                view.background = view.context.getDrawable(R.drawable.wish_ment_pink_rectangle)
            } else {
                val dateString = date.split(" ")[1].substring(5)
                view.text =
                    "$dateString ${view.context.getString(R.string.mission_result_complete)}"
                view.background = view.context.getDrawable(R.drawable.wish_ment_green_rectangle)
            }
        }
    }
}
