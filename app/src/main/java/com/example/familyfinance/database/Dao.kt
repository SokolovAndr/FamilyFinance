package com.example.familyfinance.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.familyfinance.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    fun insertItem(user: User)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAllItems(): Flow<List<User>>

    @Query("DELETE FROM users WHERE login = :i1 AND password = :i2")
    fun delete(i1: String, i2: String)

    @Query("SELECT * FROM users WHERE login = :i1 AND password = :i2")
    fun isExists(i1: String, i2: String): LiveData<List<User>>

}