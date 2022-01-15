package com.mix.mvvm.ui.main.pro

import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.databinding.FragmentProBinding
import com.mix.mvvm.ui.main.navi.NaviViewModel

/**
 * @Date 執筆時間 2022/01/15 14:30
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class ProjectFragment : BaseVmFragment<FragmentProBinding, ProViewModel>(FragmentProBinding::inflate) {
    override fun viewModelClass(): Class<ProViewModel> = ProViewModel::class.java

    override fun initView() {
    }
}