package com.mix.mvvm.ui.main.navi

import androidx.lifecycle.MutableLiveData
import com.mix.mvvm.base.BaseViewModel
import com.mix.mvvm.base.BaseVmFragment
import com.mix.mvvm.data.bean.Navi

/**
 * @Date 執筆時間 2022/01/15 14:28
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class NaviViewModel : BaseViewModel() {

    private val repository by lazy { NaviRepository() }

    val naviList = MutableLiveData<MutableList<Navi>>()

    fun getNavi() {
        launch(
            block = {
                naviList.value = repository.getNavi()
            }
        )
    }
}