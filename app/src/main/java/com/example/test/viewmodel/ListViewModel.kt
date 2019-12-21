package com.example.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Money

class ListViewModel : ViewModel(){
    val moneys = MutableLiveData<List<Money>>()
    val moneyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val mon1 = Money(1,1,1f,3,"Бассейн")
        val mon2 = Money(2,2,1f,56,"Сон")
        val mon3 = Money(3,1,13232.31f,14,"Кухня")
        val mon5 = Money(3,2,1f,14,"Отдых")

        val mon6 = Money(3,1,1f,20,"Работм")

        val mon7 = Money(3,2,123.32f,20,"Учёба")

        val mon8 = Money(3,1,120.32f,12,"Покупки")
        val list = arrayListOf<Money>(mon1, mon2, mon3, mon5, mon6, mon7, mon8)

        moneys.value = list
        moneyLoadError.value = false
        loading.value = false
    }
}