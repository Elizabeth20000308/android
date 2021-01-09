package com.example.networkdemo.service

import com.example.networkdemo.model.Student
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentInfoService {
    @POST("GetStudentServlet")
    fun getStudentData(@Query("stuno") stuno:String): Call<List<Student>>
}