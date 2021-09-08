package com.example.volley_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    val queue = RequestQueueSingleton.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val text = findViewById<TextView>(R.id.activity_textView_justText)

        val button = findViewById<Button>(R.id.activity_button)
        button.setOnClickListener {
            queue.addToRequestQueue(getStringRequest(text))
        }
    }


    private fun getStringRequest(textView: TextView): StringRequest {
        val url = "https://www.google.com"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            { textView.text = "That didn't work!" })

        return stringRequest
    }
}