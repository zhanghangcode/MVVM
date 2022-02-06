package com.mix.mvvm.data.bean

/**
 * @Date 執筆時間 2022/02/06 22:00
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

