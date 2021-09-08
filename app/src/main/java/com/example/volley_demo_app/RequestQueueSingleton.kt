package com.example.volley_demo_app

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.Volley

class RequestQueueSingleton private constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: RequestQueueSingleton? = null

        // double check locking singleton access
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RequestQueueSingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    init {
        Log.v("INFO", "Request Queue SINGLETON initialized!")
    }

    // Init requestQueue on first access
    val requestQueue by lazy {
        Log.v("INFO", "Request Queue initialized!")
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }
}