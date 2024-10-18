package com.example.gorodbezproblem.modules

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon

data class Route<T>(
    val method: Int,
    val path: String,
    val responseCallback: (T?) -> Unit,
    val errorCallback: (VolleyError) -> Unit
)

class API(context: Context) {
    val url = "http://83.166.237.142:8000"
    val queue: RequestQueue = Volley.newRequestQueue(context)

    inline fun <reified T> fetch(route: Route<T>) {
        val jsonObjectRequest = JsonObjectRequest(
            route.method,
            "${this.url}/${route.path}",
            null,
            { response ->
                val parsedResponse = Klaxon().parse<T>(response.toString())
                route.responseCallback(parsedResponse)
            },
            { error ->
                route.errorCallback(error)
            }
        )

        this.queue.add(jsonObjectRequest)
    }
}
