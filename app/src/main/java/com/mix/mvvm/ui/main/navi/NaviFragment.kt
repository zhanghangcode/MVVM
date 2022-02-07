package com.mix.mvvm.ui.main.navi

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.mix.mvvm.R
import com.mix.mvvm.databinding.FragmentNaviBinding
import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.data.bean.Article
import com.mix.mvvm.data.bean.Navi
import com.mix.mvvm.tools.randomColor
import com.mix.mvvm.ui.detail.DetailActivity
import com.yechaoa.yutilskt.YUtils
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView
import q.rorbin.verticaltablayout.widget.TabView

/**
 * @Date 執筆時間 2022/01/15 14:27
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class NaviFragment : BaseVmFragment<FragmentNaviBinding, NaviViewModel>(FragmentNaviBinding::inflate) {

    private lateinit var mTabAdapter: MyTabAdapter
    private lateinit var mNaviList: MutableList<Navi>
    private lateinit var mArticles: MutableList<Article.ArticleDetail>

    override fun viewModelClass(): Class<NaviViewModel> = NaviViewModel::class.java

    override fun initData() {
        super.initData()
        mViewModel.getNavi()
    }

    override fun observe() {
        super.observe()
        mViewModel.naviList.observe(this, {
            YUtils.hideLoading()
            mNaviList = it
            mTabAdapter = MyTabAdapter(mNaviList)
            mBinding.verticalTabLayout.setTabAdapter(mTabAdapter)
            //デフォルト第一
            mArticles = mNaviList[0].articles
            setFlowLayout(mArticles)
        })
    }

    private fun setFlowLayout(articles: MutableList<Article.ArticleDetail>) {
        mBinding.flowLayout.adapter = object : TagAdapter<Article.ArticleDetail>(articles) {
            override fun getView(parent: FlowLayout, position: Int, s: Article.ArticleDetail): View {
                val tvTag = LayoutInflater.from(activity).inflate(
                    R.layout.item_navi, mBinding.flowLayout, false
                ) as TextView
                tvTag.text = s.title
                tvTag.setTextColor(randomColor())
                return tvTag
            }
        }
    }

    override fun setListener() {
        super.setListener()
        mBinding.verticalTabLayout.addOnTabSelectedListener(object : VerticalTabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabView?, position: Int) {
               mArticles = mNaviList[position].articles
                setFlowLayout(mArticles)
            }

            override fun onTabReselected(tab: TabView?, position: Int) {
            }
        })

        mBinding.flowLayout.setOnTagClickListener { _, position, _ ->
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.WEB_URL, mArticles[position].link)
                putExtra(DetailActivity.WEB_TITLE, mArticles[position].title)
            }
            startActivity(intent)

            return@setOnTagClickListener true
        }
    }

    inner class MyTabAdapter(private val naviList: MutableList<Navi>) : TabAdapter {
        override fun getCount(): Int = naviList.size

        override fun getBadge(position: Int): ITabView.TabBadge? = null

        override fun getIcon(position: Int): ITabView.TabIcon? = null

        override fun getTitle(position: Int): ITabView.TabTitle = ITabView
            .TabTitle
            .Builder()
            .setContent(naviList[position].name)
            .setTextColor(Color.parseColor("#FF9800"), Color.parseColor("#757575"))
            .setTextSize(16)
            .build()

        override fun getBackground(position: Int): Int = 0

    }

    override fun initView() {
        YUtils.showLoading(activity as Activity, "読み込む中")
    }
}