package com.mix.mvvm

import android.app.Application
import com.tencent.mmkv.MMKV
import com.yechaoa.yutilskt.LogUtil
import com.yechaoa.yutilskt.YUtils


/**
 * @Date 執筆時間 2022/01/18 16:11
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class App : Application() {

    companion object {
        //控制三方库的编译模式
        private const val isDebugMode = true
    }

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        initYUtils()
    }


    private fun initYUtils() {
        YUtils.init(this)
        LogUtil.setIsLog(isDebugMode)
    }
}