package com.mix.mvvm.net.interceptor

import android.content.SharedPreferences
import com.mix.mvvm.common.Config
import com.tencent.mmkv.MMKV
import com.yechaoa.yutilskt.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * @Date 執筆時間 2022/01/16 22:24
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class AddCookiesInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val stringSet = SpUtil.getStringSet(Config.COOKIE)
        for (cookie in stringSet) {
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }

}

class ReceivedCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies: HashSet<String> = HashSet()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            SpUtil.setStringSet(Config.COOKIE, cookies)
//            MMKV.defaultMMKV().encode(Config.COOKIE, cookies)
        }
        return originalResponse
    }
}