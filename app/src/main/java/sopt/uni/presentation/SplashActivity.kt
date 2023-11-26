package sopt.uni.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.databinding.ActivitySplashBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.presentation.onboarding.OnBoardingActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        isUpdateAvailable()

        lifecycleScope.launch {
            delay(2000)
            if (SparkleStorage.accessToken != null) {
                if (SparkleStorage.partnerId != -1) {
                    startActivity<HomeActivity>()
                } else {
                    startActivity<NickNameActivity>()
                }
            } else {
                if (SparkleStorage.partnerId != -1) {
                    startActivity<HomeActivity>()
                } else {
                    startActivity<OnBoardingActivity>()
                }
            }
            overridePendingTransition(0, 0)
            finish()
        }
    }

    private fun isUpdateAvailable() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE,
                )
            ) {
                SparkleStorage.setUpdateAvailableBoolean(IS_UPDATE_AVAILABLE, true)
                Log.e("subin", "${SparkleStorage.getUpdateAvailableBoolean(IS_UPDATE_AVAILABLE)}")
            } else {
                SparkleStorage.setUpdateAvailableBoolean(IS_UPDATE_AVAILABLE, false)
                Log.e("subin", "${SparkleStorage.getUpdateAvailableBoolean(IS_UPDATE_AVAILABLE)}")
            }
        }
    }

    companion object {
        const val IS_UPDATE_AVAILABLE = "IS_UPDATE_AVAILABLE"
    }
}
