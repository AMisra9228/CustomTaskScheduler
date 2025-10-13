package com.sample.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.todoapp.data.entities.Info

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(info: Info)

    @Delete
    suspend fun delInfo(info: Info)

    @Query("SELECT * FROM info_table")
    fun getAllInfo(): LiveData<List<Info>>


}