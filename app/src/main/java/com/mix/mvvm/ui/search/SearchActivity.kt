package com.mix.mvvm.ui.search

import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.databinding.ActivitySearchBinding
/**
 * @Date 執筆時間 2022/01/24 23:56
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class SearchActivity : BaseVmActivity<ActivitySearchBinding, SearchViewModel>(ActivitySearchBinding::inflate) {
    override fun viewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

}