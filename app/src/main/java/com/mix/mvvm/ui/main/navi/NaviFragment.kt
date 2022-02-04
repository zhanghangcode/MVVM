package com.mix.mvvm.ui.main.navi

import com.mix.mvvm.databinding.FragmentNaviBinding
import com.mix.mvvm.base.BaseVmFragment
import com.yechaoa.yutilskt.ToastUtil

/**
 * @Date 執筆時間 2022/01/15 14:27
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class NaviFragment : BaseVmFragment<FragmentNaviBinding, NaviViewModel>(FragmentNaviBinding::inflate) {
    override fun viewModelClass(): Class<NaviViewModel> = NaviViewModel::class.java

    override fun initView() {
        ToastUtil.show("NaviFragment")
    }
}