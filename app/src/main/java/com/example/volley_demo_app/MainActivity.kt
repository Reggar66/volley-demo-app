package com.example.volley_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
            queue.addToRequestQueue(Requests.Users.fetchUsers(ProcessAllUsersFetch()))
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

    inner class ProcessAllUsersFetch : Requests.Users.FetchAllUsersListener {
        override fun onFetchAllUsersResponse(jsonString: String) {
            val collectionType = object : TypeToken<List<User>>() {}.type
            val users: List<User> = gson.fromJson(jsonString, collectionType)

            for (user in users) {
                if (user.id == 5)
                    return

                Log.v("INFO", "\n" + user.toStringFormatted())
            }
        }

        override fun onError() {
            Log.v("INFO", "Error with fetching all users!")
        }
    }
}