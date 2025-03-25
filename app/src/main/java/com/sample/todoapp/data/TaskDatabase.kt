package com.sample.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.todoapp.data.dao.AccountDao
import com.sample.todoapp.data.dao.ItemDao
import com.sample.todoapp.data.entities.Info
import com.sample.todoapp.data.entities.Item

@Database(
    entities =[Info::class, Item::class],
    version = 2,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun itemDao(): ItemDao

    companion object{

        @Volatile
        var  INSTANCE:TaskDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TaskDatabase{

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}