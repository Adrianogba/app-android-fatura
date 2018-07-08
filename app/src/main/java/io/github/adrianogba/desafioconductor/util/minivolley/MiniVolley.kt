package io.github.adrianogba.desafioconductor.util.minivolley
import org.json.JSONObject

class MiniVolley constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun request(url: String, method: Int, params: JSONObject, completionHandler: (response: String?) -> Unit) {
        service.request(url, method, params, completionHandler)
    }
}