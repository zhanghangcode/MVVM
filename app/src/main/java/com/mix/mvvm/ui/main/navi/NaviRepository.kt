package com.mix.mvvm.ui.main.navi

import com.mix.mvvm.base.BaseRepository

/**
 * @Date 執筆時間 2022/02/07 20:28
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class NaviRepository : BaseRepository() {
    suspend fun getNavi() = apiService().getNavi().data()
}