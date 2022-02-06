package com.mix.mvvm.ui.main.home

import com.mix.mvvm.adapter.ArticleAdapter
import com.mix.mvvm.adapter.BannerImageAdapter
import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.databinding.FragmentHomeBinding
import com.yechaoa.yutilskt.DisplayUtil
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

    }

    override fun initData() {
        super.initData()
        mViewModel.getBanner()
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
    }

}