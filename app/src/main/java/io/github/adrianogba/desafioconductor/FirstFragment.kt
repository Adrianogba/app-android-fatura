package io.github.adrianogba.desafioconductor

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.github.adrianogba.desafioconductor.model.Portador
import io.github.adrianogba.desafioconductor.util.minivolley.MiniVolley
import io.github.adrianogba.desafioconductor.util.minivolley.ServiceVolley
import kotlinx.android.synthetic.main.first_fragment.*
import org.json.JSONObject

import kotlinx.android.synthetic.main.second_fragment.*
import java.text.DecimalFormat


class SecondFragment : Fragment() {

    val service = ServiceVolley()
    val miniVolley = MiniVolley(service)
    val jsonParser = JsonParser()
    val gson = Gson()

    lateinit var creditCardNumber:TextView
    lateinit var disponivel:TextView
    lateinit var gastos:TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditCardNumber = tvCreditcardNumber
        disponivel = tvDisponivel
        gastos = tvGastos


        getPortador()
        getSaldo()

    }

    fun getPortador(){

        val params = JSONObject()
        //params.put("login", "teste")

        miniVolley.request(
                "https://2hm1e5siv9.execute-api.us-east-1.amazonaws.com/dev/users/profile",
                Request.Method.GET, params)
        { response ->

            val mJson = jsonParser.parse(response) as JsonObject

            val p = gson.fromJson(mJson, Portador::class.java)

            creditCardNumber.text=p.lastfour()

            //Toast.makeText(this, p.cardNumber, Toast.LENGTH_LONG).show()

        }

    }

    fun getSaldo(){

        val params = JSONObject()
        //params.put("login", "teste")

        miniVolley.request(
                "https://2hm1e5siv9.execute-api.us-east-1.amazonaws.com/dev/resume",
                Request.Method.GET, params)
        { response ->

            val mJson = jsonParser.parse(response) as JsonObject

            val dec = DecimalFormat("###,###,##0.00")

            disponivel.text= dec.format(mJson.get("balance").toString().toDouble()) + "R$"



        }

    }

}