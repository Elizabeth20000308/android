package com.example.networkdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkdemo.adapter.StudentAdapter
import com.example.networkdemo.model.Student
import com.example.networkdemo.service.StudentInfoService
import com.example.networkdemo.service.StudentService
import com.example.networkdemo.util.ServerCreator
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_allstudent.*
import kotlinx.android.synthetic.main.activity_allstudent.drawerLayout
import kotlinx.android.synthetic.main.activity_allstudent.navView
import kotlinx.android.synthetic.main.activity_allstudent.toorbar
import kotlinx.android.synthetic.main.activity_studentinfo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllStudentInfoActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search->Toast.makeText(this,"hhhh",Toast.LENGTH_LONG).show()
            android.R.id.home->drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allstudent);
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
                    intent.data= Uri.parse("tel:10086")
                    startActivity(intent)
                }
                R.id.navMail->{
                    var intent = Intent(Intent.ACTION_SENDTO)
                    intent.data= Uri.parse("mailto:sjy2000@edu.njfu.cn")
                    startActivity(intent)
                }
            }
            true
        })

        //初始化列表
        student_list();
        //设置搜索功能
        search_input.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(stuno: String?): Boolean {
                if (stuno != null) {
                    search_student_by_stuno(stuno)
                };
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                //通过首字符筛选内容
                return false
            }
        })
    }

    //使用学号模糊搜索
    fun search_student_by_stuno(stuno:String){
        val studentInfoService= ServerCreator.create<StudentInfoService>();
        if (stuno != null) {
            studentInfoService.getStudentData(stuno).enqueue(object: retrofit2.Callback<List<Student>>{
                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                    t.printStackTrace()
                }
                override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                    var students = response.body()
                    println(students);
                    if (students != null) {

                        var linearLayoutManager = LinearLayoutManager(this@AllStudentInfoActivity)
                        recycle_view.layoutManager = linearLayoutManager
                        var adapter = StudentAdapter(students,this@AllStudentInfoActivity);
                        recycle_view.adapter = adapter
                    }
                }
            })
        }
    }

    fun student_list(){
        //1. 获取Service实例对象
        val studentService= ServerCreator.create<StudentService>();
        //2. 调用实例对象方法，由于需要网络请求，需要继续调用enqueue方法
        studentService.getStudentData().enqueue(object: Callback<List<Student>> {
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                t.printStackTrace()
            }
            //3. 请求成功！
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                var students = response.body()
                println(students);
                if (students != null) {

                    var linearLayoutManager = LinearLayoutManager(this@AllStudentInfoActivity)
                    recycle_view.layoutManager = linearLayoutManager
                    var adapter = StudentAdapter(students,this@AllStudentInfoActivity);
                    recycle_view.adapter = adapter
                }
            }
        })
    }
}