package com.sample.todoapp.login_registration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(var userName: String,
                var email: String?,
                var password: String?,
                var created: String?,
                var status: Boolean? = false,
                @PrimaryKey(autoGenerate = true)
                var id: Int? = null
)