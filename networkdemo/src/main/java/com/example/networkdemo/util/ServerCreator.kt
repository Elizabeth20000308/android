package com.example.networkdemo.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerCreator {
    private const val BASE_URL="http://47.112.96.179:8080/StudentAdmin-1.0-SNAPSHOT/"
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass:Class<T>):T= retrofit.create(serviceClass)
    inline fun <reified  T> create():T=create(T::class.java)

}
