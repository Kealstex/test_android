package com.example.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Money

class ListViewMoney : ViewModel(){
    val moneys = MutableLiveData<List<Money>>()
    val moneyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val mon1 = Money(1,1,1f,1,"name1")
        val mon2 = Money(2,2,1f,1,"name2")
        val mon3 = Money(3,1,1f,1,"name3")
        val mon5 = Money(3,2,1f,1,"name5")

        val mon6 = Money(3,1,1f,1,"name6")

        val mon7 = Money(3,2,1f,1,"name7")

        val mon8 = Money(3,1,1f,1,"name8")
        val list = arrayListOf<Money>(mon1, mon2, mon3, mon5, mon6, mon7, mon8)

        moneys.value = list
        moneyLoadError.value = false
        loading.value = false
    }
}