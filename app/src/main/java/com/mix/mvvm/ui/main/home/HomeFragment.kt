package com.mix.mvvm.ui.main.home

import android.app.Activity
import android.content.Intent
import com.mix.mvvm.adapter.ArticleAdapter
import com.mix.mvvm.adapter.BannerImageAdapter
import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.databinding.FragmentHomeBinding
import com.mix.mvvm.ui.detail.DetailActivity
import com.yechaoa.yutilskt.DisplayUtil
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.AlphaPageTransformer
import com.youth.banner.transformer.ScaleInTransformer
import kotlin.math.roundToInt

/**
 * @Date 執筆時間 2022/01/15 10:39
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class HomeFragment : BaseVmFragment<FragmentHomeBinding, HomeViewModel> (FragmentHomeBinding::inflate) {

    private  lateinit var mArticleAdapter: ArticleAdapter
    private var mPosition = -1

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        initRecyclerView()
        YUtils.showLoading(activity as Activity, "読み込む中")
    }

    override fun initData() {
        super.initData()
        mViewModel.getBanner()
        mViewModel.getArticleList(mCurrentPage)
    }


    private fun initRecyclerView() {
        mArticleAdapter = ArticleAdapter().apply {
            //开启加载动画
            animationEnable = true
            //item点击
            setOnItemClickListener { _, _, position ->
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra(DetailActivity.WEB_URL, mArticleAdapter.data[position].link)
                    putExtra(DetailActivity.WEB_TITLE, mArticleAdapter.data[position].title)
                }
                startActivity(intent)
            }

            //item子view点击
            setOnItemChildClickListener { _, _, position ->
                mPosition = position
                if (data[position].collect) {
                    mViewModel.unCollect(data[position].id)
                } else {
                    mViewModel.collect(data[position].id)
                }
            }
            //加载更多
            loadMoreModule.setOnLoadMoreListener {
                mBinding.recyclerView.postDelayed({
                    if (mCurrentSize < mTotalCount) {
                        mArticleAdapter.loadMoreModule.loadMoreEnd(true)
                    } else {
                        mCurrentPage++
                        mViewModel.getArticleList(mCurrentPage)
                    }
                }, 500)
            }

        }

        mBinding.recyclerView.adapter = mArticleAdapter
    }

    override fun observe() {
        super.observe()

        mViewModel.bannerList.observe( this, { bannerList ->
            val layoutParams = mBinding.banner.layoutParams
            layoutParams.height = (DisplayUtil.getScreenWidth() / 1.99).roundToInt()

            mBinding.banner.apply {
                addBannerLifecycleObserver(this@HomeFragment)
                setBannerGalleryEffect(10,5)
                setPageTransformer(ScaleInTransformer())
                addPageTransformer(AlphaPageTransformer())
                indicator = CircleIndicator(requireContext())
                adapter = BannerImageAdapter(bannerList)
                setDatas(bannerList)
                start()
            }
        })

        mViewModel.articleList.observe(this, {
            YUtils.hideLoading()
            mCurrentSize = it.size
            if (0 == mCurrentPage) {
                mArticleAdapter.setList(it)
            } else {
                mArticleAdapter.addData(it)
                mArticleAdapter.loadMoreModule.loadMoreComplete()
            }
        })


        mViewModel.collectState.observe(this, {
            if (it) {
                ToastUtil.show("收藏成功")
                mArticleAdapter.data[mPosition].collect = true
                mArticleAdapter.notifyItemChanged(mPosition)
            }
        })

        mViewModel.unCollectState.observe(this, {
            if (it) {
                ToastUtil.show("取消成功")
                mArticleAdapter.data[mPosition].collect = false
                mArticleAdapter.notifyItemChanged(mPosition)
            }
        })
    }

}