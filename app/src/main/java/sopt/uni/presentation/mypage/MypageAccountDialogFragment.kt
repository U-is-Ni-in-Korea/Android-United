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
            dialogTitle.setText("로그아웃 하시겠습니까?")
            btnRight.setText("로그아웃")
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
            dialogTitle.setText("계정을 탈퇴하시겠어요?")
            dialogBody.setText("모든 기록이 사라져요")
            btnRight.setText("탈퇴")
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
            dialogTitle.setText("정말 커플 연결을 해제하시겠어요?")
            dialogBody.setText("해제하면 다시 되돌릴 수 없어요")
            btnRight.setText("연결 해제")
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                // 커플 연결 해제 처리
            }
        }
    }
}
