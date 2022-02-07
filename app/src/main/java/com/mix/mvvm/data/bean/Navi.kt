package com.mix.mvvm.data.bean

/**
 * @Date 執筆時間 2022/02/07 20:30
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
data class Navi(
    val articles: MutableList<Article.ArticleDetail>,
    val cid: Int,
    val name: String
)