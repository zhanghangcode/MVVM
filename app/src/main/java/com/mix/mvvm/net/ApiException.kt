package com.mix.mvvm.net

/**
 * @Date 執筆時間 2022/01/16 21:11
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class ApiException(var code: Int, override var message: String) : RuntimeException()