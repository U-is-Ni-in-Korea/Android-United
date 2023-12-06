package sopt.uni.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.databinding.ActivityHomeBinding
import sopt.uni.presentation.common.content.ErrorCodeState
import sopt.uni.presentation.common.content.UNDECIDED
import sopt.uni.presentation.history.HistoryActivity
import sopt.uni.presentation.mypage.MypageSettingActivity
import sopt.uni.presentation.shortgame.createshortgame.CreateShortGameActivity
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity
import sopt.uni.presentation.shortgame.missionresult.MissionResultActivity
import sopt.uni.presentation.wish.WishActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import sopt.uni.util.extension.showToast
import sopt.uni.util.extension.startActivity
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private var result = ""

    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = homeViewModel

        collectErrorCode()
        moveToHistory()
        getRoundResult()
        moveToShortGame()
        moveToWish()
        moveToMyPage()
        clickHeartButton()
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

    override fun onResume() {
        super.onResume()
        result = ""
        homeViewModel.fetchHomeInfo()
    }

    private fun moveToShortGame() {
        binding.clShortGame.setOnSingleClickListener {
            homeViewModel.fetchHomeInfo()
            moveToCreateShortGame()
        }
    }

    private fun moveToCreateShortGame() = lifecycleScope.launch {
        homeViewModel.fetchHomeInfo().join()
        Timber.e("shortGameEnabled: ${homeViewModel.shortGameEnabled.value}")
        if (!homeViewModel.shortGameEnabled.value) {
            startActivity<CreateShortGameActivity>()
        } else {
            this@HomeActivity.showToast("상대가 이미 게임을 생성했습니다.")
            homeViewModel.getShortGameResult()
        }
    }

    private fun getRoundResult() {
        homeViewModel.roundResult.observe(this) { roundResult ->
            result = roundResult
            if (result == UNDECIDED) {
                MissionRecordActivity.start(
                    this@HomeActivity,
                    homeViewModel.roundGameId.value!!,
                )
            } else if (result == "") {
                return@observe
            } else {
                Timber.e("result: $result")
                MissionResultActivity.start(
                    this@HomeActivity,
                    homeViewModel.roundGameId.value!!,
                )
            }
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

    private fun clickHeartButton() {
        binding.llHeartCount.setOnSingleClickListener {
            showSnackbar(binding.root, getString(R.string.prepare_heart_function))
        }
    }

    private fun collectErrorCode() {
        lifecycleScope.launch {
            homeViewModel.errorState.collect {
                when (it) {
                    is ErrorCodeState.NoToken -> {
                        showToast("존재하지 않는 사용자입니다. 다시 로그인해주세요")
                    }

                    else -> {
                        Timber.e("error: $it")
                    }
                }
            }
        }
    }
}
