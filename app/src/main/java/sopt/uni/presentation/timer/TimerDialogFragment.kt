package sopt.uni.presentation.timer

import android.content.Intent
import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity
import sopt.uni.util.extension.setOnSingleClickListener

class TimerDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.text = getString(R.string.timer_dialog_title)
            dialogBody.text = getString(R.string.timer_dialog_body)
            btnLeft.text = getString(R.string.timer_dialog_cancel)
            btnRight.text = getString(R.string.timer_dialog_out)
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                activity?.finish()
                val intent = Intent(requireContext(), MissionRecordActivity::class.java)
                startActivity(intent)
            }
        }
    }
}