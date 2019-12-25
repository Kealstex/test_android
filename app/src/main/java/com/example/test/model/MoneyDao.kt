package com.example.test.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoneyDao {
    @Insert
    suspend fun insert(money: Money)

    @Query ("SELECT * FROM money")
    suspend fun getAllMoneys(): List<Money>

    @Query("SELECT * FROM money WHERE _uuid= :MoneyId")
    suspend fun getMoney(MoneyId: Int): Money

    @Query("SELECT * FROM money ORDER BY name DESC")
    suspend fun sortNameDesc(): List<Money>

    @Query("SELECT * FROM money ORDER BY name ASC")
    suspend fun sortNameAsc(): List<Money>

    @Query("SELECT * FROM money ORDER BY cost DESC")
    suspend fun sortCostDesc(): List<Money>

    @Query("SELECT * FROM money ORDER BY cost ASC")
    suspend fun sortCostAsc(): List<Money>

    @Query("DELETE FROM money")
    suspend fun deleteAllMoneys()


}