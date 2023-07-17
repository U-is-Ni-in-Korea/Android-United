package sopt.uni.presentation.home

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityHomeBinding
import sopt.uni.presentation.history.HistoryActivity
import sopt.uni.presentation.shortgame.CreateShortGameActivity
import sopt.uni.presentation.wish.WishActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moveToHistory()
        moveToShortGame()
        moveToWish()
    }

    private fun moveToShortGame() {
        binding.clShortGame.setOnSingleClickListener {
            startActivity<CreateShortGameActivity>()
            finish()
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
}
