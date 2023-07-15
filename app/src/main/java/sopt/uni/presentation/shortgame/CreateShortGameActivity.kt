package sopt.uni.presentation.shortgame

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityCreateShortGameBinding
import sopt.uni.util.ItemDecorations
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class CreateShortGameActivity :
    BindingActivity<ActivityCreateShortGameBinding>(R.layout.activity_create_short_game) {

    private val viewModel: CreateShortGameViewModel by viewModels()

    private val missionAdapter by lazy {
        MissionCategoryAdapter(
            goToMissionDetailClickListener = { missionIdPosition ->
                // TODO : 미션상세뷰 이동
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

    private fun setAdapter() {
        binding.rvMission.adapter = missionAdapter
        binding.rvMission.addItemDecoration(ItemDecorations(0, 9, 0, 9))
        viewModel.missionList.observe(this) {
            missionAdapter.submitList(it)
        }
    }

    private fun setClickListener() {
        binding.apply {
            ivClose.setOnSingleClickListener {
                // TODO : 종료 다이얼로그
            }
            btnCreate.setOnSingleClickListener {
                // TODO : 생성 다이얼로그 + 미션 기록 액티비티 이동
            }
        }
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
            if (length >= MAX_LENGTH) {
                view.setTextColor(view.context.getColor(R.color.Red_500))
            } else {
                view.setTextColor(view.context.getColor(R.color.Gray_200))
            }
            view.text = "$length/$MAX_LENGTH"
        }

        @JvmStatic
        @BindingAdapter("setContentLength")
        fun setWishContent(view: EditText, length: Int) {
            if (length >= MAX_LENGTH && view.isFocused) {
                view.background = view.context.getDrawable(R.drawable.bg_wish_edit_text_error)
            } else {
                view.background = view.context.getDrawable(R.drawable.bg_wish_edit_text)
            }
        }
    }
}
