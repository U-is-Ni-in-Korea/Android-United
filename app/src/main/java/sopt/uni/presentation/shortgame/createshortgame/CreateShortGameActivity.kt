package sopt.uni.presentation.shortgame.createshortgame

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityCreateShortGameBinding
import sopt.uni.presentation.entity.MissionIdPosition
import sopt.uni.presentation.shortgame.createshortgame.dialog.CreateShortGameDialogFragment
import sopt.uni.presentation.shortgame.missiondetailcreate.MissionDetailCreateActivity
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity
import sopt.uni.util.ItemDecorations
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class CreateShortGameActivity :
    BindingActivity<ActivityCreateShortGameBinding>(R.layout.activity_create_short_game) {

    private val viewModel: CreateShortGameViewModel by viewModels()

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data =
                    it.data?.parcelable<MissionIdPosition>(MissionDetailCreateActivity.MISSION_ID_POSITION) as MissionIdPosition
                selectItem(data.id, data.position)
            }
        }

    private val missionAdapter by lazy {
        MissionCategoryAdapter(
            goToMissionDetailClickListener = { missionIdPosition ->
                val intent = Intent(this, MissionDetailCreateActivity::class.java)
                intent.putExtra(MissionDetailCreateActivity.MISSION_ID_POSITION, missionIdPosition)
                activityLauncher.launch(intent)
            },
            selectMissionClickListener = { missionIdPosition ->
                selectItem(missionIdPosition.id, missionIdPosition.position)
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        setViewModelObserve()
        setAdapter()
        setClickListener()
    }

    // 화면 클릭하여 키보드 숨기기 및 포커스 제거
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onBackPressed() {
        exitDialog()
    }

    private fun setAdapter() {
        binding.rvMission.adapter = missionAdapter
        binding.rvMission.addItemDecoration(ItemDecorations(0, 9, 0, 9))
    }

    private fun setViewModelObserve() {
        viewModel.missionList.observe(this) {
            missionAdapter.submitList(it)
        }
        viewModel.isCreateSuccess.observe(this) {
            if (it) {
                MissionRecordActivity.start(
                    this,
                    viewModel.roundGameId.value
                        ?: return@observe,
                )
                finish()
            }
        }
    }

    private fun setClickListener() {
        binding.apply {
            ivClose.setOnSingleClickListener {
                exitDialog()
            }
            btnCreate.setOnSingleClickListener {
                createDialog()
            }
        }
    }

    private fun exitDialog() {
        CreateShortGameDialogFragment().apply {
            titleText =
                this@CreateShortGameActivity.resources.getString(R.string.create_short_game_exit_dialog_title)
            bodyText =
                this@CreateShortGameActivity.resources.getString(R.string.create_short_game_exit_dialog_body)
            confirmButtonText =
                this@CreateShortGameActivity.resources.getString(R.string.create_short_game_exit_dialog_exit)
            dismissButtonText =
                this@CreateShortGameActivity.resources.getString(R.string.dialog_cancel_text)
            confirmClickListener = {
                finish()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(supportFragmentManager, "")
    }

    private fun createDialog() {
        CreateShortGameDialogFragment().apply {
            titleText =
                this@CreateShortGameActivity.resources.getString(R.string.create_short_game_create_dialog_title)
            bodyText =
                this@CreateShortGameActivity.resources.getString(R.string.create_short_game_create_dialog_body)
            confirmButtonText =
                this@CreateShortGameActivity.resources.getString(R.string.create_short_game_create_dialog_create)
            dismissButtonText =
                this@CreateShortGameActivity.resources.getString(R.string.dialog_cancel_text)
            confirmClickListener = {
                viewModel.createShortGame()
                this.dismiss()
            }
            dismissClickListener = {
                this.dismiss()
            }
        }.show(supportFragmentManager, "")
    }

    private fun selectItem(id: Int, position: Int) {
        missionAdapter.setSelectedItem(position)
        viewModel.setSelectedMissionId(id)
    }

    companion object {
        private const val MAX_LENGTH = 54

        @JvmStatic
        @BindingAdapter("setContentLength")
        fun setContentLength(view: TextView, length: Int) {
            if (length > MAX_LENGTH) {
                view.setTextColor(view.context.getColor(R.color.Red_500))
            } else {
                view.setTextColor(view.context.getColor(R.color.Gray_200))
            }
            view.text = "$length/$MAX_LENGTH"
        }

        @JvmStatic
        @BindingAdapter("setContentLength")
        fun setWishContent(view: EditText, length: Int) {
            if (length > MAX_LENGTH && view.isFocused) {
                view.background = view.context.getDrawable(R.drawable.bg_wish_edit_text_error)
            } else {
                view.background = view.context.getDrawable(R.drawable.bg_wish_edit_text)
            }
        }

        @JvmStatic
        @BindingAdapter("setContentLength")
        fun setButtonEnable(view: Button, length: Int) {
            view.isEnabled = length <= MAX_LENGTH
        }
    }
}
