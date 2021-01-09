package com.example.networkdemo.service

import com.example.networkdemo.model.ResultOV
import com.example.networkdemo.model.Student
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface UserCheckService {
    @POST("UserLoginServlet")
    fun  UserCheck(@Query("userName") username:String,
                   @Query("password") password:String): Call<ResultOV>
}