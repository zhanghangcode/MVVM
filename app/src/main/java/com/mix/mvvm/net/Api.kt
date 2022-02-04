package com.mix.mvvm.net

import com.mix.mvvm.base.BaseBean
import com.mix.mvvm.data.bean.Article
import com.mix.mvvm.data.bean.Collect
import com.mix.mvvm.data.bean.Hotkey
import com.mix.mvvm.data.bean.User
import retrofit2.http.*

/**
 * @Date 執筆時間 2022/01/16 20:55
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
interface Api {
    companion object{
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    //-----------------------登録・ログイン----------------------

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): BaseBean<User>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("repassword") rePassword: String?
    ): BaseBean<User>

    //ページング
    @FormUrlEncoded
    @POST("article/query/{page}/json?")
    suspend fun getSearchList(
        @Path("page") page: Int,
        @Field("k") k: String
    ): BaseBean<Article>

    //
    @GET("hotkey/json")
    suspend fun getHotkey(): BaseBean<MutableList<Hotkey>>


    //-----------------------【 收藏 】----------------------

    //收藏文章列表
    @GET("lg/collect/list/{page}/json?")
    suspend fun getCollectList(@Path("page") page: Int): BaseBean<Collect>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): BaseBean<String>

    //取消收藏（文章列表）
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollectByArticle(@Path("id") id: Int): BaseBean<String>

    //取消收藏（我的收藏页面）
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    suspend fun unCollectByCollect(
        @Path("id") id: Int,
        @Field("originId") originId: Int
    ): BaseBean<String>
}