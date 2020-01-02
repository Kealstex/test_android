package com.example.test.viewmodel

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.test.R
import com.example.test.model.Money
import com.example.test.model.MoneyDatabase
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : BaseViewModel(application) {

    fun insertMoney(item: Money){
        launch {
            val dao = MoneyDatabase(getApplication()).moneyDao()
            dao.insert(item)
        }
    }
}