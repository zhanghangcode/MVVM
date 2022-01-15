package com.mix.mvvm.ui.main.tree

import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.databinding.FragmentTreeBinding

/**
 * @Date 執筆時間 2022/01/15 14:35
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class TreeFragment : BaseVmFragment<FragmentTreeBinding, TreeViewModel>(FragmentTreeBinding::inflate) {
    override fun viewModelClass(): Class<TreeViewModel> = TreeViewModel::class.java

    override fun initView() {
    }
}