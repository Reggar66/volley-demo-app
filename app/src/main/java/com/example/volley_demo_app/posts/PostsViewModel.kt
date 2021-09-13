package com.example.volley_demo_app.posts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.volley_demo_app.data.RequestQueueSingleton
import com.example.volley_demo_app.data.Requests
import com.example.volley_demo_app.users.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val gson = Gson()

    private val mutablePosts: MutableLiveData<List<Post>> by lazy {
        MutableLiveData<List<Post>>()
    }

    private val mutableUsers: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    val posts: LiveData<List<Post>> = mutablePosts
    val users: LiveData<List<User>> = mutableUsers

    private val mutableUserMap = mutableMapOf<Int, User>()

    val userMap: Map<Int, User> = mutableUserMap

    fun fetchPosts() {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(Requests.Posts.fetchPosts(object :
                Requests.Posts.FetchAllPostsListener {
                override fun onFetchAllPostsResponse(jsonString: String) {
                    val collectionType = object : TypeToken<List<Post>>() {}.type
                    val posts: List<Post> = gson.fromJson(jsonString, collectionType)
                    mutablePosts.value = posts.shuffled()
                }

                override fun onError() {
                    Log.e("INFO", "Fetching posts went bad.")
                }
            }))
    }

    fun fetchUser() {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(Requests.Users.fetchUsers(object :
                Requests.Users.FetchAllUsersListener {
                override fun onFetchAllUsersResponse(jsonString: String) {
                    val collectionType = object : TypeToken<List<User>>() {}.type
                    val users: List<User> = gson.fromJson(jsonString, collectionType)
                    mutableUsers.value = users
                }

                override fun onError() {
                    Log.e("INFO", "Fetching users went bad.")
                }
            }))
    }

    fun createUserMap(users: List<User>) {
        for (user in users) {
            if (mutableUserMap.containsKey(user.id))
                continue
            else {
                mutableUserMap[user.id] = user
            }
        }
    }
}