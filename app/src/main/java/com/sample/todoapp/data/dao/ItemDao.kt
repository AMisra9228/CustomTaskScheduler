package com.sample.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sample.todoapp.data.entities.Info
import com.sample.todoapp.data.entities.Item
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(item: Item)
}