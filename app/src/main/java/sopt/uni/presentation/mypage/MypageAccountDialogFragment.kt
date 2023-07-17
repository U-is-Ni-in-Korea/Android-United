package sopt.uni.presentation.mypage

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.NoBodyAction2DialogBinding
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment
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
                // 로그아웃 처리
            }
        }
    }
}

class MypageAccountDeleteDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {
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
                // 회원 탈퇴 처리
            }
        }
    }
}

class MypageAccountCoupleDisconnectDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {
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
                // 커플 연결 해제 처리
            }
        }
    }
}
