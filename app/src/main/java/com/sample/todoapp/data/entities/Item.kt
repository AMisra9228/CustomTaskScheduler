package com.sample.todoapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(var title: String,
                var description: String?,
                var priority: String?,
                var created: String?,
                @PrimaryKey(autoGenerate = true)
                var id: Int? = null
)