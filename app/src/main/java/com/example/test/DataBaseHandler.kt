package com.example.test

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "Assistant.db"
val TABLE_NAME = "Money"
val SHEMA = 1
val COL_EXPENSE = "Expense"
val COL_REVENUE = "Revenue"
val COL_CASH = "Cash"
val COL_ID = "_Id"

class DataBaseHandler (var context: Context,
                       val DATABASE_NAME: String = "Assistant.db",
                       val TABLE_NAME: String = "Money",
                       val SHEMA: Int = 1,
                       val COL_EXPENSE: String = "Expense",
                       val COL_REVENUE: String = "Revenue",
                       val COL_CASH: String = "Cash",
                       val COL_ID: String = "_Id"): SQLiteOpenHelper(context, DATABASE_NAME, null,SHEMA ){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE IF NOT EXISTS " + DATABASE_NAME + "." + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EXPENSE + " REAL," +
                COL_REVENUE + " REAL," +
                COL_CASH + "REAL);"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME + "." + TABLE_NAME)
        onCreate(db)
    }

    @Throws(SQLiteConstraintException::class)
    fun clear(){
        var db = writableDatabase
        val deleteTable = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME +";"
        db.execSQL(deleteTable)
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteByID(p0: SQLiteDatabase, Id : Int){
        var deleteItem = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME +
                "WHERE " + COL_ID + "=" + Id.toString();
        p0.execSQL(deleteItem)
    }

    override fun close() {
        super.close()
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(money: Money){
        val db = writableDatabase
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

    fun readAllMoney(): ArrayList<Money> {
        val moneys = ArrayList<Money>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id: Int
        var revenue: Float
        var expense: Float
        var cash: Float
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                revenue = cursor.getFloat(cursor.getColumnIndex(COL_REVENUE))
                expense = cursor.getFloat(cursor.getColumnIndex(COL_EXPENSE))
                cash = cursor.getFloat(cursor.getColumnIndex(COL_CASH))

                moneys.add(Money(id, revenue, expense, cash))
                cursor.moveToNext()
            }
        }
        return moneys
    }

    companion object {
        private val SQL_CREATE_ENTRIES =  "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EXPENSE + " REAL," +
                COL_REVENUE + " REAL," +
                COL_CASH + "REAL);"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

}