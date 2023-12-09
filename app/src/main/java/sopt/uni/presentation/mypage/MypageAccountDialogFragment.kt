package sopt.uni.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.databinding.NoBodyAction2DialogBinding
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.di.ServicePool
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.presentation.login.LoginActivity
import sopt.uni.util.extension.setOnSingleClickListener

class MypageAccountLogoutDialogFragment :
    BindingDialogFragment<NoBodyAction2DialogBinding>(R.layout.no_body_action2_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText(getString(R.string.logout_dialog_title))
            btnRight.setText(getString(R.string.logout_message))
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                SparkleStorage.clear()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                dismiss()
            }
        }
    }
}

class MypageAccountDeleteDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {

    private val myPageAccountViewModel by viewModels<MyPageAccountViewModel>() {
        MyPageAccountViewModelFactory(
            ServicePool.myPageService,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText(getString(R.string.delete_dialog_title))
            dialogBody.setText(getString(R.string.delete_dialog_descirption))
            btnRight.setText(getString(R.string.delete_message))
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                myPageAccountViewModel.deleteUser()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                dismiss()
            }
        }
    }
}

class MypageAccountCoupleDisconnectDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {

    private val myPageAccountViewModel by viewModels<MyPageAccountViewModel>() {
        MyPageAccountViewModelFactory(
            ServicePool.myPageService,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText(getString(R.string.disconnect_dialog_title))
            dialogBody.setText(getString(R.string.disconnect_dialog_description))
            btnRight.setText(getString(R.string.disconnect_message))
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                myPageAccountViewModel.disconnectCouple()
                SparkleStorage.partnerId = NO_PARTNER
                val intent = Intent(requireContext(), NickNameActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                dismiss()
            }
        }
    }

    companion object {
        const val NO_PARTNER = -1
    }
}
