package com.mix.mvvm.data.room

import androidx.room.*
import com.mix.mvvm.data.bean.History
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * @Date 執筆時間 2022/01/15 21:06
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
@Dao
interface HistoryDao {

    @Query("SELECT * FROM t_history WHERE type=:type ORDER BY insert_time DESC")
    fun getAll(type: Int = 1): Flow<List<History>>

    @ExperimentalCoroutinesApi
    fun getAllDistinctUntilChanged() = getAll().distinctUntilChanged()

    @Query("SELECT id FROM t_history WHERE name = :name")
    fun queryIdByName(name: String): Int?

    @Insert
    fun insert(history: History)

    @Update
    fun update(history: History)

    @Delete
    fun delete(history: History)

    @Query("DELETE FROM t_history WHERE id = :id")
    fun deleteByID(id: Int)

    @Query("DELETE FROM t_history")
    fun deleteAll()

}