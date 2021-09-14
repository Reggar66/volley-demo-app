package com.example.volley_demo_app.posts.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.volley_demo_app.Photo
import com.example.volley_demo_app.data.RequestQueueSingleton
import com.example.volley_demo_app.data.Requests
import com.example.volley_demo_app.posts.Post
import com.example.volley_demo_app.users.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val gson = Gson()

    private val mutablePost: MutableLiveData<Post> by lazy {
        MutableLiveData<Post>()
    }
    private val mutableUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
    private val mutablePhoto: MutableLiveData<Photo> by lazy {
        MutableLiveData<Photo>()
    }
    private val mutableComments: MutableLiveData<List<Comment>> by lazy {
        MutableLiveData<List<Comment>>()
    }

    val post: LiveData<Post> = mutablePost
    val user: LiveData<User> = mutableUser
    val photo: LiveData<Photo> = mutablePhoto
    val comments: LiveData<List<Comment>> = mutableComments

    fun fetchPost(postId: Int) {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(
                Requests.Posts.fetchPost(
                    postId,
                    object : Requests.Posts.FetchSinglePostListener {
                        override fun onFetchPostResponse(jsonString: String) {
                            val post = gson.fromJson(jsonString, Post::class.java)
                            mutablePost.value = post
                        }

                        override fun onError() {
                            Log.e("INFO", "Error: post")
                        }
                    })
            )
    }

    fun fetchUser(userId: Int) {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(
                Requests.Users.fetchUser(
                    userId,
                    object : Requests.Users.FetchSingleUserListener {
                        override fun onFetchUserResponse(jsonString: String) {
                            val user = gson.fromJson(jsonString, User::class.java)
                            mutableUser.value = user
                        }

                        override fun onError() {
                            Log.e("INFO", "Error: user")
                        }
                    })
            )
    }

    fun fetchPhoto(userId: Int) {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(
                Requests.Photos.fetchPhoto(
                    userId,
                    object : Requests.Photos.FetchSinglePhotoListener {
                        override fun onFetchPhotoResponse(jsonString: String) {
                            val photo = gson.fromJson(jsonString, Photo::class.java)
                            mutablePhoto.value = photo
                        }

                        override fun onError() {
                            Log.e("INFO", "Error: user")
                        }
                    })
            )
    }

    fun fetchComments(postId: Int) {
        RequestQueueSingleton.getInstance(getApplication())
            .addToRequestQueue(
                Requests.Comments.fetchPostComments(
                    postId,
                    object : Requests.Comments.FetchCommentsListener {
                        override fun onResponse(jsonString: String) {
                            val collectionType = object : TypeToken<List<Comment>>() {}.type
                            val comments: List<Comment> = gson.fromJson(jsonString, collectionType)
                            mutableComments.value = comments
                        }

                        override fun onError() {
                            Log.e("INFO", "Error: comments")
                        }
                    })
            )
    }

}