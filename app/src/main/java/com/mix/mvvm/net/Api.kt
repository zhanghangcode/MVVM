package com.mix.mvvm.net

import com.mix.mvvm.base.BaseBean
import com.mix.mvvm.data.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
}