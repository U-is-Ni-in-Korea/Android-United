package sopt.uni.presentation.wish.fragment

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.TitleAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener

class NewWishDialogFragment :
    BindingDialogFragment<TitleAction2DialogBinding>(R.layout.title_action2_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText(getString(R.string.new_wish_dialog_title))
            dialogBody.setText(getString(R.string.new_wish_dialog_description))
            btnRight.setText(getString(R.string.dialog_ok_text))
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                activity?.finish()
            }
        }
    }
}
