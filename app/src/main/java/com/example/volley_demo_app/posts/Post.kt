package com.example.volley_demo_app.posts

import android.util.Log
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
class Post(val userId: Int, val id: Int, val title: String, val body: String) {

    // JsonPlaceHolder doesn't have those fields so we fake them.
    var likes: Int = Random.nextInt(0, 500)
    var isLiked:Boolean = false
    var reShares: Int = Random.nextInt(0, 500)

    // Since JSON doesn't have this field and it's stupid to fetch comments
    // just to get number of them for every single post we fake it (it's just demo)
    var comments: Int = Random.nextInt(0, 500)
}