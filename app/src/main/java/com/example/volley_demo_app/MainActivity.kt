package com.example.volley_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.volley_demo_app.data.RequestQueueSingleton
import com.example.volley_demo_app.data.Requests
import com.example.volley_demo_app.users.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private val queue = RequestQueueSingleton.getInstance(this)

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val text = findViewById<TextView>(R.id.activity_textView_justText)

        val button = findViewById<Button>(R.id.activity_button)
        button.setOnClickListener {
            queue.addToRequestQueue(Requests.Users.fetchUser(1, ProcessUserFetch()))
        }
    }

    inner class ProcessUserFetch : Requests.Users.FetchSingleUserListener {
        override fun onFetchUserResponse(jsonString: String) {
            val user = gson.fromJson(jsonString, User::class.java)
            Log.v("INFO", "Fetched user:\n" + user.toStringFormatted())
        }

        override fun onError() {
            Log.v("INFO", "Error with fetching user!")
        }
    }
}