package io.github.adrianogba.desafioconductor.util.minivolley

import org.json.JSONObject

interface ServiceInterface {
    fun request(url: String,method: Int, params: JSONObject, completionHandler: (response: String?) -> Unit)

}
