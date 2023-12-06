package sopt.uni.presentation

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import sopt.uni.R
import sopt.uni.databinding.ActivitiyWebViewBinding
import sopt.uni.util.binding.BindingActivity

class WebViewActivity : BindingActivity<ActivitiyWebViewBinding>(R.layout.activitiy_web_view) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWebView()
        startWebView()
    }

    private fun initWebView() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
        }
    }

    private fun startWebView() {
        intent.getStringExtra("url")?.let {
            binding.webView.loadUrl(it)
        }
    }
}
