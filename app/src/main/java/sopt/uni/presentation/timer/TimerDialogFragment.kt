package sopt.uni.presentation.timer

import android.content.Intent
import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.presentation.mypage.MypageSettingActivity
import sopt.uni.util.extension.setOnSingleClickListener

class TimerDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText("타이머가 아직 끝나지 않았어요")
            dialogBody.setText("타이머는 종료되지 않지만 종료 알림을 받\n을 수 없어요")
            btnLeft.setText("취소")
            btnRight.setText("나가기")
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                activity?.finish()
                val intent = Intent(requireContext(), MypageSettingActivity::class.java)
                startActivity(intent)
            }
        }
    }
}