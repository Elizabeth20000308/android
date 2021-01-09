package com.example.networkdemo.util

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtil{
    //无参网络请求
    fun sendOkHttpRequest(url:String,callback:okhttp3.Callback){
        val client=OkHttpClient();
        val request=Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(callback)
    }
    //有参网络请求
    fun sendOkHttpRequest(url:String,data:Map<String,String>,callback:okhttp3.Callback){
        val client=OkHttpClient() ;
        val requestBody=FormBody.Builder()
            for (key in data.keys){
                requestBody.add(key,data.get(key).toString())
            }
        val request=Request.Builder()
            .url(url)
            .post(requestBody.build())
            .build()
        client.newCall(request).enqueue(callback)
    }
}