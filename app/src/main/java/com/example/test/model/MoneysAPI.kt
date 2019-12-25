package com.example.test.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface MoneysAPI {
    @GET("Kealstex/test_android/master/moneys.json")
    fun getMoneys(): Single<List<Money>>

}