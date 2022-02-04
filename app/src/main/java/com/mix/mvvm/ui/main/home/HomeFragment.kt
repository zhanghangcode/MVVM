package com.mix.mvvm.ui.main.home

import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.databinding.FragmentHomeBinding
import com.yechaoa.yutilskt.ToastUtil

/**
 * @Date 執筆時間 2022/01/15 10:39
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class HomeFragment : BaseVmFragment<FragmentHomeBinding, HomeViewModel> (FragmentHomeBinding::inflate) {

//    private  lateinit var mArticleAdapter: ArticleAdapter
    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        ToastUtil.show("initView")
    }

}