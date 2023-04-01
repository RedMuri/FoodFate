package com.example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.db.models.UserDbModel

@Dao
interface UsersDao {

    @Query("select * from users where phone == :phone")
    suspend fun getUserByPhone(phone: String): UserDbModel?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(userDbModel: UserDbModel)

}