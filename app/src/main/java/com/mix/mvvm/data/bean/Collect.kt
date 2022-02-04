package com.mix.mvvm.data.bean

/**
 * @Date 執筆時間 2022/02/04 19:32
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
data class Collect(
    val curPage: Int,
    val datas: MutableList<CollectDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
) {
    data class CollectDetail(
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val originId: Int,
        val publishTime: Long,
        val title: String,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )
}