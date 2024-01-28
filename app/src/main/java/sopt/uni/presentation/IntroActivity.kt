package sopt.uni.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.repository.shortgame.ShortGameRepository
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.presentation.home.UpdateDialogFragment
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.presentation.invite.ShareInviteCodeActivity
import sopt.uni.presentation.onboarding.OnBoardingActivity
import sopt.uni.util.extension.startActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : AppCompatActivity(), UpdateDialogFragment.UpdateDialogListener {
    @Inject
    lateinit var shortGameRepository: ShortGameRepository

    @Inject
    lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        checkUpdateAvailability()

        // 메모장 초기화
        shortGameRepository.setMemoText("")
    }

    private fun checkUpdateAvailability() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE,
                )
            ) {
                showUpdateDialog()
            } else {
                checkUserStatus()
            }
        }
        appUpdateInfoTask.addOnFailureListener { exception ->
            Timber.tag("inappUpdate").e("업데이트 체크 실패: ${exception.message}")
            checkUserStatus()
        }
    }

    override fun onDialogDismissed() {
        checkUserStatus()
    }

    override fun onUpdateComplete() {
        checkUserStatus()
    }

    private fun showUpdateDialog() {
        val dialogFragment = UpdateDialogFragment().apply {
            updateDialogListener = this@IntroActivity
        }
        dialogFragment.show(supportFragmentManager, "UpdateDialog")
    }

    private fun checkUserStatus() {
        if (SparkleStorage.accessToken != null) {
            Log.e("accessToken", SparkleStorage.accessToken.toString())
            Log.e("partnerId", SparkleStorage.partnerId.toString())
            if (SparkleStorage.partnerId != -1 && SparkleStorage.coupleId != -1) {
                startActivity<HomeActivity>()
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            } else if (SparkleStorage.partnerId == -1 && SparkleStorage.coupleId != -1) {
                startActivity<ShareInviteCodeActivity>()
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
