package com.example.test

class SecondPresenter(val db: DataBaseHandler) {

    fun insertData(money: Money) {
        db.insertData(money)
    }

    fun deleteData(){
        db.clear();
    }
}