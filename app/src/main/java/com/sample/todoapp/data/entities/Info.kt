package com.sample.todoapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info_table")
data class Info(var title: String,
                var description: String?,
                var created: Long = System.currentTimeMillis(),
                @PrimaryKey(autoGenerate = true)
                var id: Int? = null
)