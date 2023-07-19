package sopt.uni.presentation.shortgame.missiondetailcreate

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMissionDetailCreateBinding
import sopt.uni.presentation.entity.MissionIdPosition
import sopt.uni.presentation.shortgame.createshortgame.CreateShortGameActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener
@AndroidEntryPoint
class MissionDetailCreateActivity : BindingActivity<ActivityMissionDetailCreateBinding>(R.layout.activity_mission_detail_create) {

    private val viewModel: MissionDetailCreateViewModel by viewModels()

    private lateinit var idPosition: MissionIdPosition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        idPosition = intent.parcelable<MissionIdPosition>(MISSION_ID_POSITION)!!
        binding.missionDetailCreateViewModel = viewModel
        setClickListener()
    }

    private fun setClickListener() {
        binding.missionDetailCreateMissionBtnPick.setOnSingleClickListener {
            val intent = Intent(this, CreateShortGameActivity::class.java)
            intent.putExtra(MISSION_ID_POSITION, idPosition)
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.missionDetailCreateBack.setOnSingleClickListener {
            finish()
        }
    }

    companion object {
        const val MISSION_ID_POSITION = "MISSION_ID_POSITION"
    }
}
