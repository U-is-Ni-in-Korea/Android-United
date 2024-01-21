package sopt.uni.presentation.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener
import javax.inject.Inject

class UpdateDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {

    @Inject
    lateinit var appUpdateManager: AppUpdateManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText(getString(R.string.update_dialog_title))
            dialogBody.setText(getString(R.string.update_dialog_body))
            btnRight.setText(getString(R.string.update_dialog_ok))
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                updateSparkle()
            }
        }
    }

    private fun updateSparkle() {
        appUpdateManager.startUpdateFlowForResult(
            appUpdateManager.appUpdateInfo.result,
            startForResult,
            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build(),
        )
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode != Activity.RESULT_OK) {
                Toast.makeText(requireContext(), "업데이트에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT)
                    .show()
                Log.e("update", it.resultCode.toString())
            } else {
                SparkleStorage.setUpdateAvailableBoolean(
                    UpdateDialogFragment.IS_UPDATE_AVAILABLE,
                    true,
                )
            }
        }

    companion object {
        const val IS_UPDATE_AVAILABLE = "IS_UPDATE_AVAILABLE"
    }
}
