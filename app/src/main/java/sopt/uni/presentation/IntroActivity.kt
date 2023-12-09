package sopt.uni.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.presentation.onboarding.OnBoardingActivity
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkUserStatus()
    }

    private fun checkUserStatus() {
        if (SparkleStorage.accessToken != null) {
            if (SparkleStorage.partnerId != -1) {
                startActivity<HomeActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            } else {
                startActivity<NickNameActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        } else {
            if (SparkleStorage.partnerId != -1) {
                startActivity<HomeActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            } else {
                startActivity<OnBoardingActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
        overridePendingTransition(0, 0)
    }
}
