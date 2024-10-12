package com.example.gorodbezproblem

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class API {
    private val url = "http://83.166.237.142:8000";
    private val queue: RequestQueue;

    constructor(context: Context) {
        this.queue = Volley.newRequestQueue(context);
    }

    fun getRoot(responseCallback: Response.Listener<String>, errorCallback:  Response.ErrorListener) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            this.url + "/",
            responseCallback,
            errorCallback,
        )

        this.queue.add(stringRequest);
    }

    fun getProblems(responseCallback: Response.Listener<JSONObject>, errorCallback:  Response.ErrorListener) {
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            this.url + "/problems",
            null,
            responseCallback,
            errorCallback
        )

        this.queue.add(jsonRequest);
    }

    fun createProblem(responseCallback: Response.Listener<String>, errorCallback:  Response.ErrorListener) {
        // TODO: Вкрутить сюда ручку
    }
}