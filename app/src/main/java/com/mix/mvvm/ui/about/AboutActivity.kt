package com.mix.mvvm.ui.about

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Paint
import android.graphics.Typeface
import com.mix.mvvm.R
import com.mix.mvvm.base.BaseActivity
import com.mix.mvvm.databinding.ActivityAboutBinding
import com.mix.mvvm.databinding.ContentAboutBinding
import com.mix.mvvm.ui.detail.DetailActivity
import com.yechaoa.yutilskt.ShareUtil
import com.yechaoa.yutilskt.YUtils

/**
 * @Date 執筆時間 2022/02/27 20:20
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class AboutActivity : BaseActivity<ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    private lateinit var mContentAboutBinding: ContentAboutBinding

    override fun initialize() {
        super.initialize()
        mBinding.toolbar.title = "${getString(R.string.wanandroid)}  V${YUtils.getVersionName()}"
        mBinding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }

        mContentAboutBinding = mBinding.contentAbout

        mContentAboutBinding.tvAuthor.paint.flags = Paint.UNDERLINE_TEXT_FLAG

        mContentAboutBinding.tvAuthor.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.WEB_URL, getString(R.string.qiita))
                putExtra(DetailActivity.WEB_TITLE, getString(R.string.author))
            }
            startActivity(intent)
        }

        mContentAboutBinding.tvGithub.paint.flags = Paint.UNDERLINE_TEXT_FLAG

        mContentAboutBinding.tvGithub.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.WEB_URL, getString(R.string.github))
                putExtra(DetailActivity.WEB_TITLE, getString(R.string.app_name))
            }
            startActivity(intent)
        }

        mBinding.fab.setOnClickListener {
            ShareUtil.shareText(getString(R.string.wanandroid), getString(R.string.github))
        }

        setTypeface()
    }

    private fun setTypeface() {
        //获取AssetManager
        val assets = assets as AssetManager
        //根据路径得到字体
        val typeface = Typeface.createFromAsset(assets, "fonts/mononoki-Regular.ttf")
        //设置给TextView
        mContentAboutBinding.tvAuthor.typeface = typeface
        mContentAboutBinding.tvGithub.typeface = typeface
        mContentAboutBinding.tvApi.typeface = typeface
        mContentAboutBinding.tvLibrary.typeface = typeface
    }
}