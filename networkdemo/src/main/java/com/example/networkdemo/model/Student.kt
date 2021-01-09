package com.example.networkdemo.model

class Student(val stuNo:String,
              val stuName:String,
              val classes:String,
              val gender:String,
              val department:String,
              val tel:String,
              val dormNo:String,
              val photoPath: String

) {
  override fun toString(): String {
    return "Student(stuNo='$stuNo', stuname='$stuName', classes='$classes', gender='$gender', department='$department', tel='$tel', dormNo='$dormNo', photoPath='$photoPath')"
  }
}