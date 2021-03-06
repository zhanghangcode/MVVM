package com.mix.mvvm.ui.main.home

import com.mix.mvvm.base.BaseRepository

/**
 * @Date 執筆時間 2022/02/06 21:58
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class HomeRepository : BaseRepository() {

    suspend fun getBanner() = apiService().getBanner().data()

    suspend fun getArticleList(page: Int) = apiService().getArticleList(page).data()

    suspend fun collect(id: Int) = apiService().collect(id)

    suspend fun unCollectByArticle(id: Int) = apiService().unCollectByArticle(id)
}