package com.example.familyfinance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.familyfinance.models.Account
import com.example.familyfinance.models.Category
import com.example.familyfinance.models.Record
import com.example.familyfinance.models.User

@Database(entities = arrayOf(User::class, Category::class, Account::class, Record::class), version = 6)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "familyFinance.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}
