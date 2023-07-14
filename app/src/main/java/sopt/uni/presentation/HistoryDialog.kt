package sopt.uni.presentation

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.TitleAction1DialogBinding
import sopt.uni.util.extension.setOnSingleClickListener

// 예시용 입니다! 히스토리는 다이얼로그 없어용
class HistoryDialog :
    BindingDialogFragment<TitleAction1DialogBinding>(R.layout.title_action1_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 텍스트 넣기
        binding.dialogBody.setText("내용임둥")
        binding.dialogTitle.setText("제목임둥")
        // 클릭시 로직 처리
        binding.btnPositive.setOnSingleClickListener {
            dismiss()
        }
    }
}
