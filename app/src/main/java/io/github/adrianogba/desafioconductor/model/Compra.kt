package io.github.adrianogba.desafioconductor.model

import java.text.SimpleDateFormat

class Compra{
    lateinit var date:String
    lateinit var store:String
    lateinit var description:String
    lateinit var value:String


    fun dateForLabel(): String {


        // treat "Z" as literal
        //val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        //sdf.timeZone = TimeZone.getTimeZone("UTC")


        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        //val output = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val output = SimpleDateFormat("dd/MM")
        val d = sdf.parse(date)
        val formattedTime = output.format(d)



        return formattedTime
    }


}