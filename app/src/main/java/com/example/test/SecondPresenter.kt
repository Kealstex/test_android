package com.example.test

import com.example.test.model.Money

class SecondPresenter(val db: DataBaseHandler) {

    fun insertData(money: Money) {
        db.insertData(money)
    }

    fun deleteData(){
        db.clear();
    }
}