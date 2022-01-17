package com.mix.mvvm.net

import android.util.Log
import com.mix.mvvm.net.interceptor.AddCookiesInterceptor
import com.mix.mvvm.net.interceptor.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Date 執筆時間 2022/01/16 21:43
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
object RetrofitClient {
    private const val CALL_TIMEOUT = 10L
    private const val CONNECT_TIMEOUT = 20L
    private const val IO_TIMEOUT = 20L

    private var apiService: Api

    fun getApiService(): Api {
        return apiService
    }

    init {

        val loggingInterceptor = HttpLoggingInterceptor { Log.d("httpsLog", it) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        /**OkHttpClient*/
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .build()

        /**Retrofit*/
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /**ApiService*/
        apiService = retrofit.create(Api::class.java)
    }
}