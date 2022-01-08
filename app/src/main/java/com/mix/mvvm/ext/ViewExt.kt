package com.mix.mvvm.ext

import android.icu.util.DateInterval
import android.view.View
import android.widget.TextView

/**
 * @Date 執筆時間 2022/01/07 20:34
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */

var lastClickTime = 0L
fun View.setOnClickNoRepeat(interval: Long = 500, onClick: (View) -> Unit){
    setOnClickListener{
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)){
            return@setOnClickListener
        }
        lastClickTime = currentTime
        onClick.invoke(it)
    }
}