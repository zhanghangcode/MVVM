package com.mix.mvvm

import android.app.Application
import com.tencent.mmkv.MMKV




/**
 * @Date 執筆時間 2022/01/18 16:11
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}