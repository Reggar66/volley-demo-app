package com.example.volley_demo_app.users

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.volley_demo_app.data.RequestQueueSingleton
import com.example.volley_demo_app.data.Requests
import com.example.volley_demo_app.data.Requests.Users.FetchAllUsersListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val gson = Gson()

    private val mutableAllUsers: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    private val mutableUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    // Used to observe list of users
    val allUsers: LiveData<List<User>> = mutableAllUsers
    val user: LiveData<User> = mutableUser

    fun fetchAllUsers() {
        RequestQueueSingleton
            .getInstance(getApplication())
            .addToRequestQueue(Requests.Users.fetchUsers(object : FetchAllUsersListener {
                override fun onFetchAllUsersResponse(jsonString: String) {
                    val collectionType = object : TypeToken<List<User>>() {}.type
                    val users: List<User> = gson.fromJson(jsonString, collectionType)
                    mutableAllUsers.value = users
                    Log.v("INFO", users.toString())
                }

                override fun onError() {
                    Log.v("INFO", "Error with fetching all users!")
                }
            }))
    }

    fun fetchUser(userId: Int) {
        RequestQueueSingleton
            .getInstance(getApplication())
            .addToRequestQueue(
                Requests.Users.fetchUser(
                    userId,
                    object : Requests.Users.FetchSingleUserListener {
                        override fun onFetchUserResponse(jsonString: String) {
                            val user = gson.fromJson(jsonString, User::class.java)
                            mutableUser.value = user
                            Log.v("INFO", user.toString())
                        }

                        override fun onError() {
                            Log.v("INFO", "Error with fetching single user!")
                        }

                    })
            )
    }
}