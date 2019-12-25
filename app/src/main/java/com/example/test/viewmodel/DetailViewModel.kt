package com.example.test.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Money
import com.example.test.model.MoneyDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val moneyLiveData = MutableLiveData<Money>()

    fun fetch(uuid: Int){
        launch {
            val mon1 = MoneyDatabase(getApplication()).moneyDao().getMoney(uuid)
            moneyLiveData.value = mon1
        }
    }
}