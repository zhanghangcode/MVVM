package com.mix.mvvm.base

import com.mix.mvvm.net.Api
import com.mix.mvvm.net.RetrofitClient

/**
 * @Date 執筆時間 2022/01/29 11:32
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
open class BaseRepository {
    protected fun apiService(): Api {
        return RetrofitClient.getApiService()
    }
}