package com.example.familyfinance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "password")
    var password: String,
)

