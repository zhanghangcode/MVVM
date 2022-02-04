package com.mix.mvvm.ui.search

import androidx.lifecycle.MutableLiveData
import com.mix.mvvm.base.BaseViewModel
import com.mix.mvvm.data.bean.Article
import com.mix.mvvm.data.bean.Hotkey

/**
 * @Date 執筆時間 2022/01/24 23:57
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class SearchViewModel : BaseViewModel() {

    private val repository by lazy {
        SearchRepository()
    }

    val hotkeyList = MutableLiveData<MutableList<Hotkey>>()

    fun getHotkey() {
        launch(
            block = {
                hotkeyList.value = repository.getHotkey()
            }
        )
    }

    val articleList = MutableLiveData<MutableList<Article.ArticleDetail>>()

    fun getArticleList(page: Int, key: String) {
        launch(
            block = {
                articleList.value = repository.getSearchList(page, key).datas
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