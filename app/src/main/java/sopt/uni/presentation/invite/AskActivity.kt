package sopt.uni.presentation.invite

import android.content.Intent
import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivtiyAskBinding
import sopt.uni.presentation.WebViewActivity
import sopt.uni.presentation.mypage.MypageAccountCoupleDisconnectDialogFragment
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

class AskActivity : BindingActivity<ActivtiyAskBinding>(R.layout.activtiy_ask) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackButton()
        moveToAskPage()
        secesstionUser()
    }

    private fun setupBackButton() {
        binding.btnAskBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun moveToAskPage() {
        binding.clAskQna.setOnSingleClickListener {
            Intent(this, WebViewActivity::class.java).apply {
                putExtra("url", resources.getString(R.string.ask_url))
            }.run(::startActivity)
        }
    }

    private fun secesstionUser() {
        binding.clAskSecession.setOnSingleClickListener {
            MypageAccountCoupleDisconnectDialogFragment().show(
                supportFragmentManager,
                "MypageAccountCoupleDisconnectDialogFragment",
            )
        }
    }
}
