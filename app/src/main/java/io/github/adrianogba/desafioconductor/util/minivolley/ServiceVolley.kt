package io.github.adrianogba.desafioconductor.util.minivolley

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.util.HashMap

class ServiceVolley : ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName

    override fun request(url: String, method: Int, params: JSONObject, completionHandler: (response: String?) -> Unit) {
        val jsonStrReq = object : StringRequest(method, url,
                Response.Listener<String> { response ->
                    Log.d(TAG, "/request OK! Resposta: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/request falhou! Erro: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        BackendVolley.instance?.addToRequestQueue(jsonStrReq, TAG)
    }


}