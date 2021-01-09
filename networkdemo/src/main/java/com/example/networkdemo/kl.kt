package com.example.networkdemo

import kotlinx.coroutines.*

fun  main(){
    runBlocking {
        launch {
            for (i in 1..10){
                println("coroutine"+i)
            }
        }
        launch {
            for (i in 1..10){
                println("coroutine2"+i)
            }
            test()
        }
    }
}

/**
 * 把函数test声明为suspend，挂起函数不能直接运行
 */
suspend fun test(){
    delay(1000)
    println("helloworld!")

    val start=System.currentTimeMillis()
    runBlocking {
        val result1=async {
            delay(1000)
            3+3
        }
        val result2=async {
            delay(1000)
            3+5
        }
    }
}