package com.example.volley_demo_app.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.volley_demo_app.data.RequestQueueSingleton
import com.example.volley_demo_app.data.Requests
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val gson = Gson()

    private val mutablePosts: MutableLiveData<List<Post>> by lazy {
        MutableLiveData<List<Post>>()
    }

    val posts: LiveData<List<Post>> = mutablePosts

    fun fetchPosts() {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(Requests.Posts.fetchPosts(object :
                Requests.Posts.FetchAllPostsListener {
                override fun onFetchAllPostsResponse(jsonString: String) {

                    val collectionType = object : TypeToken<List<Post>>() {}.type
                    val posts: List<Post> = gson.fromJson(jsonString, collectionType)
                    mutablePosts.value = posts
                }

                override fun onError() {
                    TODO("Not yet implemented")
                }
            }))


    }

}