package com.mix.mvvm.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.mix.mvvm.base.BaseViewModel
import com.mix.mvvm.data.bean.Article
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

    val articleList = MutableLiveData<MutableList<Article.ArticleDetail>>()

    fun getArticleList(page: Int) {
        launch(
            block = {
                articleList.value = repository.getArticleList(page).datas
            }
        )
    }

    val collectState = MutableLiveData<Boolean>()

    fun collect(id: Int) {
        launch(
            block = {
                collectState.value = 0 == repository.collect(id).code()
            }
        )
    }

    val unCollectState = MutableLiveData<Boolean>()

    fun unCollect(id: Int) {
        launch(
            block = {
                unCollectState.value = 0 == repository.unCollectByArticle(id).code()
            }
        )
    }
}