package com.mix.mvvm.router.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.mix.mvvm.router.ROUTER_PATH_SETTING

/**
 * @Date 執筆時間 2022/01/24 23:25
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
@Interceptor(priority = 8, name = "ログインフィルタ")
class LoginInterceptor : IInterceptor {
    override fun init(context: Context?) {
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
//        if (needIntercept(postcard.path)) {
//
//        }
    }

    private fun needIntercept(path: String): Boolean {
        return path in arrayOf(
            ROUTER_PATH_SETTING, // 設定
        )
    }

}