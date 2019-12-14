package com.example.test

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.test.model.Money

class DataBaseHandler (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null,SHEMA ){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    @Throws(SQLiteConstraintException::class)
    fun clear(){
        val db = this.writableDatabase
        db.execSQL(SQL_DELETE_ENTRIES)
        Toast.makeText(context, "Удалено!",Toast.LENGTH_LONG).show()
    }

    fun viewData(money: Money){
        Toast.makeText(context,"name ="+money.name + " cost=" + money.cost.toString() +" is+-=" + money.flow.toString() + " time=" + money.time.toString(), Toast.LENGTH_SHORT).show()
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteByID(p0: SQLiteDatabase, Id : Int){
        var deleteItem = "DELETE FROM " + TABLE_NAME +
                "WHERE " + COL_ID + "=" + Id.toString();
        p0.execSQL(deleteItem)
    }

    override fun close() {
        super.close()
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(money: Money){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_COST, money.cost)
        cv.put(COL_IS_REVENUE, money.flow)
        cv.put(COL_NAME, money.name)
        cv.put(COL_TIME,money.time)
        val result = db.insert(TABLE_NAME, null, cv)
        db.close()
        if( result == -1.toLong()){
            Toast.makeText(context, "Insert Money Error", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context,"Success!", Toast.LENGTH_SHORT).show()
        }

    }

    fun readAllMoney(): ArrayList<Money> {
        val moneys = ArrayList<Money>()
        val db = this.writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id: Int = 12
        var cost: Float
        var isRevenue: Int
        var name: String
        var time: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                cost = cursor.getFloat(cursor.getColumnIndex(COL_COST))
                isRevenue = cursor.getInt(cursor.getColumnIndex(COL_IS_REVENUE))
                time = cursor.getInt(cursor.getColumnIndex(COL_TIME))
                name = cursor.getString(cursor.getColumnIndex(COL_NAME))

                moneys.add(
                    Money(
                        id,
                        isRevenue,
                        cost,
                        time,
                        name
                    )
                )
                cursor.moveToNext()
            }
        }
        return moneys
    }

    companion object {

        private const val DATABASE_NAME = "Assistant.db"
        private const val TABLE_NAME = "Money"
        private val SHEMA = 1
        private const val COL_COST = "Cost"
        private const val COL_IS_REVENUE = "IsRevenue"
        private const val COL_ID = "_Id"
        private const val COL_TIME = "Time"
        private const val COL_NAME = "Name"

        private val SQL_CREATE_ENTRIES =  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_IS_REVENUE + " INTEGER," +
                COL_COST + " REAL," +
                COL_TIME + " INTEGER," +
                COL_NAME + " TEXT);"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

}