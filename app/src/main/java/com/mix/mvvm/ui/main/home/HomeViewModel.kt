package com.mix.mvvm.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.mix.mvvm.base.BaseViewModel
import com.mix.mvvm.data.bean.Banner

/**
 * @Date 執筆時間 2022/01/15 12:38
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class HomeViewModel : BaseViewModel() {

    private val repository by lazy { HomeRepository() }

    val bannerList = MutableLiveData<MutableList<Banner>>()

    fun getBanner() {
        launch(
            block = {
                bannerList.value = repository.getBanner()
            }
        )
    }
}