package com.mix.mvvm.ui.search

import com.mix.mvvm.base.BaseRepository

/**
 * @Date 執筆時間 2022/01/29 11:32
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class SearchRepository : BaseRepository() {

    suspend fun getHotkey() = apiService().getHotkey().data()

    suspend fun getSearchList(page: Int, key: String) = apiService().getSearchList(page, key).data()

    suspend fun collect(id: Int) = apiService().collect(id)

    suspend fun unCollectByArticle(id: Int) = apiService().unCollectByArticle(id)
}