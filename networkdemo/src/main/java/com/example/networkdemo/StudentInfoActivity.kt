package com.example.networkdemo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.networkdemo.model.Student
import com.example.networkdemo.service.StudentInfoService
import com.example.networkdemo.util.ServerCreator
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_allstudent.*
import kotlinx.android.synthetic.main.activity_studentinfo.*
import kotlinx.android.synthetic.main.activity_studentinfo.drawerLayout
import kotlinx.android.synthetic.main.activity_studentinfo.navView
import kotlinx.android.synthetic.main.activity_studentinfo.toorbar
import retrofit2.Call
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class StudentInfoActivity : AppCompatActivity() {


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentinfo)
        //重写样式
        setSupportActionBar(toorbar);
        //菜单栏按钮
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        //滑动菜单栏设置
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener(){ it ->
            drawerLayout.closeDrawers()
            when(it.itemId){
                R.id.navCall ->{
                    var intent = Intent(Intent.ACTION_DIAL)
                    intent.data=Uri.parse("tel:10086")
                    startActivity(intent)
                }
                R.id.navMail->{
                    var intent = Intent(Intent.ACTION_SENDTO)
                    intent.data=Uri.parse("mailto:sjy2000@edu.njfu.cn")
                    startActivity(intent)
                }
            }
            true
        })



        var stuname = intent.getStringExtra("stuname")
        var stuno = intent.getStringExtra("stuno")
        val studentInfoService= ServerCreator.create<StudentInfoService>();
        if (stuno != null) {
            studentInfoService.getStudentData(stuno).enqueue(object: retrofit2.Callback<List<Student>>{
                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                    var students = response.body()
                    println(students);
                    if (students != null){
                        stu_info_stuNo.text=students[0].stuNo
                        stu_info_stuName.text=students[0].stuName
                        stu_info_gender.text=students[0].gender
                        stu_info_classes.text=students[0].classes
                        stu_info_department.text=students[0].department
                        stu_info_dormno.text=students[0].dormNo
                        stu_info_tel.text=students[0].tel

                        val path1 = students[0].photoPath.split(".");

                        val url = "http://47.112.96.179:8080/StudentAdmin-1.0-SNAPSHOT" + path1[1] + ".jpg"
                        //    println(path)
                        val handler = object : Handler() {
                            override fun handleMessage(msg: Message) {
                                //更新imaefview
                                when (msg.what) {
                                    1 -> {
                                        val image = msg.obj as Bitmap
                                        stu_info_photo.setImageBitmap(image)
                                    }
                                }
                            }
                        }
                        thread {
                            var myurl = URL(url)
                            var conn = myurl.openConnection() as HttpURLConnection
                            conn.connectTimeout = 6000
                            conn.doInput = true
                            conn.useCaches = false
                            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                                println("ok")
                                var imgbit = conn.inputStream
                                var bmp = BitmapFactory.decodeStream(imgbit)

                                val msg = Message()
                                msg.what = 1;
                                msg.obj=bmp;
                                handler.sendMessage(msg)
                            }
                        }
                    }
                }
            })
        }
    }
}