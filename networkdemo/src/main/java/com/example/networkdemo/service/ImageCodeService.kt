package com.example.networkdemo.service

import android.media.Image
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST

interface ImageCodeService {
    @POST("ImageCodeServlet")
    fun ImageCodeService():Call<ResponseBody>
}