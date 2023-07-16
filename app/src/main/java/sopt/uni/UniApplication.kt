package sopt.uni

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import sopt.uni.data.datasource.local.SparkleStorage
import timber.log.Timber

@HiltAndroidApp
class UniApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SparkleStorage.init(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
