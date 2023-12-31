package com.example.familyfinance.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.familyfinance.models.Account
import com.example.familyfinance.models.Category
import com.example.familyfinance.models.Ostatok
import com.example.familyfinance.models.Record
import com.example.familyfinance.models.Recordnew
import com.example.familyfinance.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    //USERS
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

    //CATEGORIES
    @Insert
    fun insertCategory(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT name FROM categories ORDER BY id")
    fun getAllCategoriesNames(): Flow<Array<String>>

    @Query("SELECT * FROM categories WHERE direction = :dir")
    fun getAllCategoriesByDir(dir: Boolean): Flow<List<Category>>

    @Query("SELECT id FROM categories WHERE name = :i")
    fun getCategoryId(i: String): Int

    //ACCOUNTS
    @Insert
    fun insertAccount(account: Account)

    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): Flow<List<Account>>

    @Query("SELECT name FROM accounts ORDER BY id")
    fun getAllAccountsNames(): Flow<Array<String>>

    @Query("SELECT id FROM accounts WHERE name = :i")
    fun getAccountId(i: String): Int

    //RECORDS
    @Insert
    fun insertRecord(record: Record)

    @Query("SELECT * FROM records")
    fun getAllRecords(): Flow<List<Record>>

    @Query("SELECT name FROM records JOIN categories ON records.categoryId = categories.id WHERE categories.name =:i")
    fun getNameFromCategoryById(i: String): Int

    @Query("SELECT records.id AS id, categories.name AS cat, accounts.name  AS acc, records.sum, records.date AS date2 FROM records JOIN categories ON records.categoryId = categories.id JOIN accounts ON records.accountId = accounts.id")
    fun getMyRecords(): Flow<List<Recordnew>>

    @Query("select accounts.name AS accName, sum(\n" +
            "CASE\n" +
            "WHEN categories.direction = 0  \n" +
            "        THEN records.sum*-1 \n" +
            "    WHEN categories.direction = 1  \n" +
            "        THEN records.sum\n" +
            "ELSE records.sum\n" +
            "end\n" +
            ") AS totalSum\n" +
            "From accounts JOIN records on accounts.id= records.accountId \n" +
            "JOIN categories on categories.id = records.categoryId\n" +
            "group by  accounts.id")
    fun getOstatki(): Flow<List<Ostatok>>
}

