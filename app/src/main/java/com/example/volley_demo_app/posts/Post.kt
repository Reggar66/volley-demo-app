package com.example.volley_demo_app.posts

import android.util.Log
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
class Post(val userId: Int, val id: Int, val title: String, val body: String) {
    val likes: Int = Random.nextInt(0, 500)
}