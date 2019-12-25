package com.example.test.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Money
import com.example.test.model.MoneyDatabase
import com.example.test.model.MoneysAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel (application: Application) : BaseViewModel(application){

    private val moneysAPIService = MoneysAPIService()
    private val disposable =CompositeDisposable()

    val moneys = MutableLiveData<List<Money>>()
    val moneyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromDatabase()
    }

    private fun fetchFromDatabase(){
        loading.value = true
        /*disposable.add(
            moneysAPIService.getMoneys()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Money>>() {
                    override fun onSuccess(t: List<Money>) {
                        storeMoneysLocally(t)
                    }

                    override fun onError(e: Throwable) {
                        moneyLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })

        )*/
        getMoneys()
    }

    private fun  moneysRetrieved(moneysList: List<Money>){
        moneys.value = moneysList
        moneyLoadError.value = false
        loading.value = false
    }

    /*private fun storeMoneysLocally(list: List<Money>){
        launch{
            val dao = MoneyDatabase(getApplication()).moneyDao()
            dao.deleteAllMoneys()
            val result = dao.insertAll(*list.toTypedArray())
            var i =0
            while (i < list.size){
                list[i]._uuid = result[i].toInt()
                ++i
            }
            moneysRetrieved(list)
        }
    }*/

    private fun insertMoney(list: Money){
        launch {
            val dao = MoneyDatabase(getApplication()).moneyDao()
            dao.insert(list)
            val newList = dao.getAllMoneys()
            moneysRetrieved(newList)
        }
    }

    private fun getMoneys(){
        launch{
            val dao = MoneyDatabase(getApplication()).moneyDao()
            val result = dao.getAllMoneys()
            moneysRetrieved(result)
        }
    }

    private fun sortByNameDesc(){
        launch{
            val dao = MoneyDatabase(getApplication()).moneyDao()
            val result = dao.sortNameDesc()
            moneysRetrieved(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}