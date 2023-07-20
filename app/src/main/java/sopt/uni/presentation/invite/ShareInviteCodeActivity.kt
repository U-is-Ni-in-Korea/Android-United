package sopt.uni.presentation.invite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sopt.uni.R
import sopt.uni.databinding.ActivityShareInviteCodeBinding
import sopt.uni.presentation.common.content.INVITECODE
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.UiState
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showToast
import sopt.uni.util.extension.startActivity
import timber.log.Timber

@AndroidEntryPoint
class ShareInviteCodeActivity :
    BindingActivity<ActivityShareInviteCodeBinding>(R.layout.activity_share_invite_code) {
    private val shareInviteCodeViewModel by viewModels<ShareInviteCodeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moveToPrevPage()
        getInviteCode()
        checkCoupleConnection()
        copyInviteCode()
    }

    private fun getInviteCode() {
        Timber.e(intent.getStringExtra(INVITECODE))
        binding.tvInviteCode.text = intent.getStringExtra(INVITECODE)
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }

    private fun checkCoupleConnection() {
        binding.llCheckConnection.setOnSingleClickListener {
            shareInviteCodeViewModel.checkCoupleConnection()
            collectConnectState()
        }
    }

    private fun collectConnectState() {
        shareInviteCodeViewModel.connectedState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    if (it.data) {
                        this@ShareInviteCodeActivity.showToast(getString(R.string.couple_connect_success))
                        startActivity<HomeActivity>()
                        finishAffinity()
                    } else {
                        this@ShareInviteCodeActivity.showToast(getString(R.string.cannot_connect))
                    }
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun copyInviteCode() {
        binding.btnCopyInviteCode.setOnSingleClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.tvInviteCode.text.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
