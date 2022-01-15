package com.mix.mvvm.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mix.mvvm.data.bean.History

/**
 * @Date 執筆時間 2022/01/15 19:23
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase(){

    abstract fun historyDao(): HistoryDao

    companion object{
        private const val DATABASE_NAME = "history.db"
        private lateinit var mDatabase: HistoryDatabase

        fun getInstance(context: Context): HistoryDatabase {
            if (!this::mDatabase.isInitialized) {
                //创建的数据库的实例
                mDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return mDatabase
        }
    }

}