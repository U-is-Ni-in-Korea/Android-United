package sopt.uni.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityHomeBinding
import sopt.uni.presentation.history.HistoryActivity
import sopt.uni.presentation.mypage.MypageSettingActivity
import sopt.uni.presentation.shortgame.createshortgame.CreateShortGameActivity
import sopt.uni.presentation.wish.WishActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val homeViewModel by viewModels<HomeViewModel>()

    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = homeViewModel

        moveToHistory()
        moveToShortGame()
        moveToWish()
        moveToMyPage()
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.exit_text),
                Toast.LENGTH_SHORT,
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun moveToShortGame() {
        binding.clShortGame.setOnSingleClickListener {
            startActivity<CreateShortGameActivity>()
        }
    }

    private fun moveToWish() {
        binding.clWishBanner.setOnSingleClickListener {
            startActivity<WishActivity>()
        }
    }

    private fun moveToHistory() {
        binding.llGameHistory.setOnSingleClickListener {
            startActivity<HistoryActivity>()
        }
    }

    private fun moveToMyPage() {
        binding.ivProfile.setOnSingleClickListener {
            startActivity<MypageSettingActivity>()
        }
    }
}
