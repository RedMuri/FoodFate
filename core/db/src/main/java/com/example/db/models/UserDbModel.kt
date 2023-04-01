package com.example.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.db.AppDatabase

@Entity(tableName = AppDatabase.USERS_TABLE_NAME)
data class UserDbModel(
    val name: String,
    val phone: String,
    val password: String,
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
)
