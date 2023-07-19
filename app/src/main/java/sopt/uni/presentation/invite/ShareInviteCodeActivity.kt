package sopt.uni.presentation.invite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.databinding.ActivityShareInviteCodeBinding
import sopt.uni.presentation.common.content.INVITECODE
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
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
        binding.btnCheckConnection.setOnSingleClickListener {
            shareInviteCodeViewModel.checkCoupleConnection()
            lifecycleScope.launch {
                shareInviteCodeViewModel.isConnected.collect { isConnected ->
                    if (isConnected) {
                        startActivity<HomeActivity>()
                    } else {
                        showSnackbar(binding.root, getString(R.string.cannot_connect))
                    }
                }
            }
        }
    }
}
