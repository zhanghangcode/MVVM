package com.mix.mvvm.ui.detail

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.view.Gravity
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebViewClient
import com.mix.mvvm.base.BaseActivity
import com.mix.mvvm.databinding.ActivityDetailBinding
import com.yechaoa.yutilskt.LogUtil

/**
 * @Date 執筆時間 2022/02/04 18:16
 * @Author Caden
 * @Description 詳細WEB画面
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {
    companion object {
        const val WEB_URL: String = "web_url"
        const val WEB_TITLE: String = "web_title"
    }

    private lateinit var mAgentWeb: AgentWeb

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initialize() {
        super.initialize()
        mBinding.tvTitle.text = Html.fromHtml(intent.getStringExtra(WEB_TITLE), Html.FROM_HTML_MODE_COMPACT)
        mBinding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
        initAgentWeb()
    }

    private fun initAgentWeb() {
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mBinding.webContent, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebViewClient(mWebViewClient)
            .createAgentWeb()
            .ready()
            .go(intent.getStringExtra(WEB_URL))

        val webView = mAgentWeb.webCreator.webView
        //获取手势焦点
        webView.requestFocusFromTouch()
        webView.settings.apply {
            //サポートJS
            javaScriptEnabled = true
            //ズームをサポートするかどうか
            setSupportZoom(true)
            builtInZoomControls = true
            //ズームボタンを表示するかどうか
            displayZoomControls = false
            //アダプティブスクリーン
            useWideViewPort = true
            loadWithOverviewMode = true
        }
        addBGChild(mAgentWeb.webCreator.webParentLayout as FrameLayout)
    }

    private fun addBGChild(frameLayout: FrameLayout) {
        val title = "AgentWebが提供するテクノロジー"
        val mTextView = TextView(frameLayout.context)
        mTextView.text = title
        mTextView.textSize = 16f
        mTextView.setTextColor(Color.parseColor("#727779"))
        frameLayout.setBackgroundColor(Color.parseColor("#272b2d"))
        val mFlp = FrameLayout.LayoutParams(-2, -2)
        mFlp.gravity = Gravity.CENTER_HORIZONTAL
        val scale: Float = frameLayout.context.resources.displayMetrics.density
        mFlp.topMargin = (15 * scale + 0.5f).toInt()
        frameLayout.addView(mTextView, 0, mFlp)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    private val mWebViewClient: WebViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            //url取得
            val uri = request!!.url
            LogUtil.i(uri.toString())
            /**
             * URLは通常、プロトコル、ドメイン名、ポート、パス、およびURLアドレスのパラメーターで構成されます。
             * https://baike.baidu.com/item/android/60243
             * uri.scheme プロトコル https
             * uri.authority ドメイン名 baike.baidu.com
             * uri.path URLアドレス item/android/60243
             * uri.queryParameterNames パラメーター null
             * uri.getQueryParameter("id")
             */
            return super.shouldOverrideUrlLoading(view, request)
        }


        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            LogUtil.i("url---$url")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            LogUtil.i("url---$url")
        }
    }
    /**
     * アクティビティまたはフラグメントのライフサイクルに従って、CPUを解放すると電力を節約できます。
     */
    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}