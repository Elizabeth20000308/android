package com.example.networkdemo.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.networkdemo.R
import com.example.networkdemo.StudentInfoActivity
import com.example.networkdemo.model.Student
import kotlinx.android.synthetic.main.activity_register.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class StudentAdapter (val students:List<Student>,val context: Context): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val stuno:TextView=view.findViewById(R.id.info_stuNo)
            val stuname:TextView=view.findViewById(R.id.info_stuName)
            val gender:TextView=view.findViewById(R.id.info_gender)
//            var photo:ImageView=view.findViewById(R.id.info_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.student_item, parent, false);
        var viewHolder = ViewHolder(view);
        viewHolder.itemView.setOnClickListener {
            var intent = Intent(context, StudentInfoActivity::class.java);

            intent.putExtra("stuname",viewHolder.stuname.text.toString());
            intent.putExtra("stuno",viewHolder.stuno.text.toString());
            context.startActivity(intent);
        }
        return viewHolder;
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return students.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val student = students[position]

        holder.stuno.text = student.stuNo;
        holder.stuname.text = student.stuName;
        holder.gender.text = student.gender;

//        val path1 = student.photoPath.split(".");
//        val path = student.photoPath.split(".","/");
//        val url = "http://47.112.96.179:8080/studentadmin-1.0-SNAPSHOT" + path1[1] + ".jpg"
//        //    println(path)
//        val handler = object : Handler() {
//            override fun handleMessage(msg: Message) {
//                //更新imaefview
//                when (msg.what) {
//                    1 -> {
//                        val image = msg.obj as Bitmap
//                        holder.photo.setImageBitmap(image)
//                    }
//                }
//            }
//        }
//        thread {
//            var myurl = URL(url)
//            var conn = myurl.openConnection() as HttpURLConnection
//            conn.connectTimeout = 6000
//            conn.doInput = true
//            conn.useCaches = false
//            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
//                println("ok")
//                var imgbit = conn.inputStream
//                var bmp = BitmapFactory.decodeStream(imgbit)
//
//                val msg = Message()
//                msg.what = 1;
//                msg.obj=bmp;
//                handler.sendMessage(msg)
//            }
//        }
    }
}



