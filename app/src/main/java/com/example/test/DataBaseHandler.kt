package com.example.test

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//val DATABASE_NAME = "Assistant.db"
//val TABLE_NAME = "Money"
//val SHEMA = 1
//val COL_EXPENSE = "Expense"
//val COL_REVENUE = "Revenue"
//val COL_CASH = "Cash"
//val COL_ID = "Id"

class DataBaseHandler (var context: Context,
                       val DATABASE_NAME: String = "Assistant.db",
                       val TABLE_NAME: String = "Money",
                       val SHEMA: Int = 1,
                       val COL_EXPENSE: String = "Expense",
                       val COL_REVENUE: String = "Revenue",
                       val COL_CASH: String = "Cash",
                       val COL_ID: String = "_Id"): SQLiteOpenHelper(context, DATABASE_NAME, null,SHEMA ){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EXPENSE + " REAL," +
                COL_REVENUE + " REAL," +
                COL_CASH + "REAL)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun clear(p0: SQLiteDatabase){
        val deleteTable = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME +";"
        p0.execSQL(deleteTable)
    }

    fun deleteByID(p0: SQLiteDatabase, Id : Int){
        var deleteItem = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME +
                "WHERE " + COL_ID + "=" + Id.toString();
        p0.execSQL(deleteItem)
    }

    override fun close() {
        super.close()
    }

    fun insertData(money: Money){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID, money.Id)
        cv.put(COL_CASH, money.cash)
        cv.put(COL_EXPENSE, money.expense)
        cv.put(COL_REVENUE, money.revenue)
        var result = db.insert(TABLE_NAME, null, cv)

        if( result == -1.toLong()){
            Toast.makeText(context, "Insert Money Error", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context,"Success!", Toast.LENGTH_SHORT).show()
        }

    }

}
