package com.example.test.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Money::class), version=1)
abstract class MoneyDatabase : RoomDatabase(){

    abstract fun moneyDao(): MoneyDao

    companion object{
        @Volatile private var instance: MoneyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            MoneyDatabase::class.java,
            "moneydatabase"
        ).build()


    }
}