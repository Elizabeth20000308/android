package com.example.networkdemo

import com.example.networkdemo.AllStudentInfoActivity
import com.example.networkdemo.R
import com.example.networkdemo.Register

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.networkdemo.model.ResultOV
import com.example.networkdemo.service.UserCheckService
import com.example.networkdemo.util.HttpUtil
import com.example.networkdemo.util.ServerCreator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.toorbar
import okhttp3.ResponseBody

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toorbar);

        login_register.setOnClickListener {
            val IntentRegister: Intent = Intent(this, Register::class.java);
            startActivity(IntentRegister)
        }

        val IntentAllStudentInfo: Intent = Intent(this, AllStudentInfoActivity::class.java);
        login_submit.setOnClickListener {
            var username = Login_username.text.toString()
            var password = Login_password.text.toString()
            var UserCheckService = ServerCreator.create<UserCheckService>()
            UserCheckService.UserCheck(username, password).enqueue(object :retrofit2.Callback<ResultOV>{
                override fun onFailure(call: retrofit2.Call<ResultOV>, t: Throwable) {
                    t.printStackTrace()
                }
                override fun onResponse(
                    call: retrofit2.Call<ResultOV>,
                    response: retrofit2.Response<ResultOV>
                ) {
                    var body = response.body()
                    if (body?.result =="ok"){
                        startActivity(IntentAllStudentInfo)
                    } else {
                        Toast.makeText(applicationContext,"信息输入错误",Toast.LENGTH_SHORT).show()

                    }
                }

            })

        }



    }
}

