package sopt.uni.presentation.memo

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMemoBinding
import sopt.uni.presentation.shortgame.createshortgame.dialog.CreateShortGameDialogFragment
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class MemoActivity : BindingActivity<ActivityMemoBinding>(R.layout.activity_memo) {
    private val viewModel: MemoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.memoViewModel = viewModel
        binding.etMemo.requestFocus()
        binding.btnTimerBack.setOnSingleClickListener {
            exitDialog()
        }
    }

    override fun onBackPressed() {
        exitDialog()
    }

    private fun exitDialog() {
        this.let {
            CreateShortGameDialogFragment().apply {
                titleText =
                    it.resources.getString(R.string.memo_exit_dialog_title)
                bodyText =
                    it.resources.getString(R.string.memo_exit_dialog_body)
                confirmButtonText =
                    it.resources.getString(R.string.create_short_game_exit_dialog_exit)
                dismissButtonText =
                    it.resources.getString(R.string.dialog_cancel_text)
                confirmClickListener = {
                    it.finish()
                    this.dismiss()
                }
                dismissClickListener = {
                    this.dismiss()
                }
            }.show(it.supportFragmentManager, "")
        }
    }
}
