package com.example.familyfinance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.sql.Date


@Entity(
    tableName = "records",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("accountId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)

data class Record(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    /*@ColumnInfo(name = "userId")
    var userId: Int? = null,*/

    @ColumnInfo(name = "categoryId")
    var categoryId: Int? = null,

    @ColumnInfo(name = "accountId")
    var accountId: Int? = null,

    @ColumnInfo(name = "sum")
    var sum: Long,

    @ColumnInfo(name = "date")
    var date: String

)
