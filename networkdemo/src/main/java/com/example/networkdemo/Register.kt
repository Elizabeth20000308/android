package com.example .networkdemo

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.networkdemo.MainActivity
import com.example.networkdemo.model.ResultOV
import com.example.networkdemo.service.ImageCodeService
import com.example.networkdemo.service.UserCheckService
import com.example.networkdemo.service.UserSaveService
import com.example.networkdemo.util.HttpUtil
import com.example.networkdemo.util.ServerCreator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStream

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toorbar);

        //验证码
        var ImageCodeService = ServerCreator.create<ImageCodeService>()
        ImageCodeService.ImageCodeService().enqueue(object :retrofit2.Callback<ResponseBody>{
            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@Register, "网络连接错误", Toast.LENGTH_LONG).show();
            }
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response!=null) {
                    val inputStream: InputStream? = response.body()?.byteStream()
                    println(response.body())
                    println(inputStream)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    image_code.setImageBitmap(bitmap)
//                    session.getAttribute(uri)
                }
            }

        })

        register_submit.setOnClickListener {
            var username = register_username.text.toString();
            var password = register_password.text.toString();
            var password2 = register_password2.text.toString();
            var intentsuccess = Intent(this, MainActivity::class.java)
            if (password == password2) {
                if (username != ""&&password!="") {

                    var create = ServerCreator.create<UserSaveService>()
                    create.UserSave(username, password).enqueue(object :retrofit2.Callback<ResultOV>{
                        override fun onFailure(call: retrofit2.Call<ResultOV>, t: Throwable) {
                            Toast.makeText(this@Register, "网络连接错误", Toast.LENGTH_LONG).show();
                        }

                        override fun onResponse(
                            call: retrofit2.Call<ResultOV>,
                            response: retrofit2.Response<ResultOV>
                        ) {
                            var resultOV = response.body()
                            if (resultOV?.result  =="ok"){
                                Toast.makeText(this@Register, "注册成功", Toast.LENGTH_LONG).show();
                                startActivity(intentsuccess)
                            }else{
                                Toast.makeText(this@Register, "注册失败", Toast.LENGTH_LONG).show();
                            }
                        }

                    })

                } else {
                    Toast.makeText(this, "用户名,密码不能为空", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "两次密码不一致，请重新输入密码", Toast.LENGTH_LONG).show();
                register_password.text = null;
                register_password2.text = null;
            }

        }



    }
}