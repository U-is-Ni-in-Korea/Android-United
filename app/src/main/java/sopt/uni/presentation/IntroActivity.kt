package sopt.uni.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.repository.shortgame.ShortGameRepository
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.presentation.onboarding.OnBoardingActivity
import sopt.uni.util.extension.startActivity
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {
    @Inject
    lateinit var shortGameRepository: ShortGameRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        checkUserStatus()

        // 메모장 초기화
        shortGameRepository.setMemoText("")
    }

    private fun checkUserStatus() {
        if (SparkleStorage.accessToken != null) {
            Log.e("accessToken", SparkleStorage.accessToken.toString())
            Log.e("partnerId", SparkleStorage.partnerId.toString())
            if (SparkleStorage.partnerId != -1) {
                startActivity<HomeActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            } else {
                startActivity<NickNameActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        } else {
            startActivity<OnBoardingActivity>()
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        overridePendingTransition(0, 0)
    }
}
