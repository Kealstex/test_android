package com.example.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Money

class DetailViewModel : ViewModel() {
    val moneyLiveData = MutableLiveData<Money>()

    fun fetch(){
        val mon1 = Money(1,1,1f,1,"name1")
        moneyLiveData.value = mon1
    }
}