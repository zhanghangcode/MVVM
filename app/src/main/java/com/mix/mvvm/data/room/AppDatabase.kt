package com.mix.mvvm.data.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.mix.mvvm.data.bean.History

/**
 * @Date 執筆時間 2022/02/04 20:32
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun historyDao(): HistoryDao

  companion object {
    private const val DATABASE_NAME = "history.db"

    fun getInstance(context: Context): AppDatabase{
       return Room.databaseBuilder(
         context.applicationContext,
         AppDatabase::class.java,
         DATABASE_NAME
       ).build()
    }
  }
}