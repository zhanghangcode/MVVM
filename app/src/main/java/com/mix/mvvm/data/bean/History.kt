package com.mix.mvvm.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Date 執筆時間 2022/01/15 19:25
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
@Entity(tableName = "t_history")
data class History(

    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    val id: Int? =null,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    val name: String?,

    @ColumnInfo(name = "insert_time", typeAffinity = ColumnInfo.TEXT)
    val insertTime: String?,

    @ColumnInfo(name = "type", typeAffinity = ColumnInfo.INTEGER)
    val type: Int = 1
)