package com.mix.mvvm.tools

import android.graphics.Color
import java.util.*

/**
 * @Date 執筆時間 2022/02/04 21:51
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */

fun randomColor(): Int {
    Random().run {
        val red = nextInt(210)
        val green = nextInt(210)
        val blue = nextInt(210)
        return Color.rgb(red, green, blue)
    }
}