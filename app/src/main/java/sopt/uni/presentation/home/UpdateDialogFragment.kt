package sopt.uni.presentation.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.util.binding.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UpdateDialogFragment(private val onDismissOrComplete: () -> Unit) :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {

    @Inject
    lateinit var appUpdateManager: AppUpdateManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutSizeRatio(0.778f, 0.2f)

        with(binding) {
            dialogTitle.setText(getString(R.string.update_dialog_title))
            dialogBody.setText(getString(R.string.update_dialog_body))
            btnRight.setText(getString(R.string.update_dialog_ok))
            btnLeft.setOnSingleClickListener {
                dismiss()
                onDismissOrComplete()
            }
            btnRight.setOnSingleClickListener {
                updateSparkle()
            }
        }
    }

    private fun updateSparkle() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    startForResult,
                    AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build(),
                )
            }
        }.addOnFailureListener {
            Timber.tag("updateSparkleFail").e("업뎃실패ㅠ")
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode != Activity.RESULT_OK) {
                showSnackbar(binding.root, "업데이트에 실패했습니다. 다시 시도해주세요.")
                Log.e("update", it.resultCode.toString())
            } else {
                SparkleStorage.setUpdateAvailableBoolean(
                    IS_UPDATE_AVAILABLE,
                    true,
                )
                onDismissOrComplete()
            }
        }

    companion object {
        const val IS_UPDATE_AVAILABLE = "IS_UPDATE_AVAILABLE"
    }
}
