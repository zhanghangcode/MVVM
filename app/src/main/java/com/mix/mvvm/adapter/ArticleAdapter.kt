package com.mix.mvvm.adapter

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mix.mvvm.R
import com.mix.mvvm.data.bean.Article

/**
 * @Date 執筆時間 2022/01/18 19:49
 * @Author Caden
 * @z
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class ArticleAdapter : BaseQuickAdapter<Article.ArticleDetail, BaseViewHolder>(R.layout.item_article) {

    init {
        addChildClickViewIds(R.id.article_favorite)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun convert(holder: BaseViewHolder, item: Article.ArticleDetail) {
        holder.setText(R.id.article_title, Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT))
        holder.setText(R.id.article_chapter, item.chapterName)
        holder.setText(R.id.article_date, item.niceDate)

        if (item.collect) {
            Glide.with(context).load(R.mipmap.ic_like_checked).into(holder.getView(R.id.article_favorite))
        } else {
            Glide.with(context).load(R.mipmap.ic_like_normal).into(holder.getView(R.id.article_favorite))
        }
    }

}