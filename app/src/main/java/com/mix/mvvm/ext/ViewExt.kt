package com.mix.mvvm.ext

import android.icu.util.DateInterval
import android.view.View
import android.widget.TextView
import java.net.CacheResponse
import java.util.*

/**
 * @Date 執筆時間 2022/01/07 20:34
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */

var lastClickTime = 0L
fun View.setOnClickNoRepeat(interval: Long = 500, onClick: (View) -> Unit){
    setOnClickListener{
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)){
            return@setOnClickListener
        }
        lastClickTime = currentTime
        onClick.invoke(it)
    }
}


fun main(){
    login("zh"){ msg: String, code: Int ->

        "d"
    }

    login("zh", ::method)

    val like = { msg: String -> String
        "like$msg"
    }
   println(like("like"))

    val nimi = show(1)
    println(nimi("3"))
    println(nimi)
}

fun method(msg: String, code: Int) : String{
    println("like")
   return "jjj"
}

val show = {}

inline fun login(userName: String, response: (String, Int) -> String){
    var j = response("", 200);
}

fun show(info : Int) : (String) -> String {
    return {name: String ->
        "hhh$name"
    }
}

