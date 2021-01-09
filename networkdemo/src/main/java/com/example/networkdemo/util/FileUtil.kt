package com.example.networkdemo.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

object FileUtil {
    public fun getStringFromInpuutStream(input:InputStream):String{
        val resultStr:StringBuilder = java.lang.StringBuilder();
        val inputStreamReader = InputStreamReader(input);//字节流转化为字符流

        val breader = BufferedReader(inputStreamReader)//缓冲作用
        breader.use{
            breader.forEachLine {
                resultStr.append(it)
            }
        }
        return resultStr.toString();
    }
}