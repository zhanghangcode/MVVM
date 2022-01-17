package com.mix.mvvm.base

import com.mix.mvvm.net.ApiException

/**
 * @Date 執筆時間 2022/01/16 21:07
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class BaseBean<T>(private val errorCode: Int,
                  private val data:T,
                  private val errorMsg:String?) {

    fun code(): Int {
        if (errorCode == 0) {
            return errorCode
        } else {
            throw ApiException(errorCode, errorMsg ?: "")
        }
    }

    fun data(): T {
        if (errorCode == 0) {
            return data
        } else {
            throw ApiException(errorCode, errorMsg ?: "")
        }
    }
}