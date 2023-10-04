package sopt.uni.presentation.shortgame.createshortgame

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityCreateShortGameBinding
import sopt.uni.presentation.shortgame.missiondetailcreate.MissionDetailCreateFragment
import sopt.uni.util.binding.BindingActivity

@AndroidEntryPoint
class CreateShortGameActivity :
    BindingActivity<ActivityCreateShortGameBinding>(R.layout.activity_create_short_game) {

    private val shortGameFragment by lazy { ShortGameFragment.newInstance() }
    private val missionDetailCreateFragment by lazy { MissionDetailCreateFragment.newInstance() }

    private val viewModel: CreateShortGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        changeFragment(getString(R.string.label_short_game))
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

    fun changeFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(tag) ?: when (tag) {
            getString(R.string.label_mission_detail) -> missionDetailCreateFragment
            getString(R.string.label_short_game) -> shortGameFragment
            else -> null
        } ?: return

        if (fragment.isVisible) return

        val transaction = fragmentManager.beginTransaction()

        fragmentManager.fragments.filter { it.isVisible }
            .forEach {
                transaction.hide(it)
            }

        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.fcv_create_short_game, fragment, tag)
        }

        transaction.commit()
    }
}
