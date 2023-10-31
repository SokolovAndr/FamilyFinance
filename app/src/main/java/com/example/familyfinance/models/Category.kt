package com.example.familyfinance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")

data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "direction")
    var direction: Boolean,
)
{
    override fun toString(): String {
        return name
    }
}


