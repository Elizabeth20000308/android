package com.example.networkdemo.service

import com.example.networkdemo.model.ResultOV
import com.example.networkdemo.model.Student
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface UserSaveService {
    @POST("RegisterServlet")
    fun  UserSave(@Query("username") username:String,
                   @Query("password") password:String): Call<ResultOV>
}