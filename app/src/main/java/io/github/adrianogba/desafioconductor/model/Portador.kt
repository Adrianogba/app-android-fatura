package io.github.adrianogba.desafioconductor.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Portador {

    lateinit var name: String
    lateinit var cardNumber: String
    lateinit var expirationDate: String

    @SuppressLint("SimpleDateFormat")
    fun expirationDateAsNative(): String {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val d = sdf.parse(expirationDate)
        return output.format(d)
    }

    fun lastfour():String{

        val a = cardNumber.toCharArray()

        return "XXXX XXXX XXXX "+a[12]+a[13]+a[14]+a[15]


    }

}