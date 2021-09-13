package com.example.volley_demo_app.data

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest

class Requests {

    object Users {
        interface FetchSingleUserListener {
            fun onFetchUserResponse(jsonString: String)
            fun onError()
        }

        interface FetchAllUsersListener {
            fun onFetchAllUsersResponse(jsonString: String)
            fun onError()
        }

        /**
         * Creates [StringRequest] that fetches one user with given id.
         * @param userId id of a user to fetch
         * @param listener listener used to pass fetched result to. Use [FetchSingleUserListener]
         * @return [StringRequest] to be used with [RequestQueueSingleton]
         */
        @JvmStatic
        fun fetchUser(userId: Int, listener: FetchSingleUserListener?): StringRequest {
            val url = "https://jsonplaceholder.typicode.com/users/$userId"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    listener?.onFetchUserResponse(response)
                },
                { listener?.onError() })

            return stringRequest
        }

        /**
         * Creates [StringRequest] that fetches all users.
         * @param listener listener used to pass fetched result to. Use [FetchAllUsersListener]
         * @return [StringRequest] to be used with [RequestQueueSingleton]
         */
        @JvmStatic
        fun fetchUsers(listener: FetchAllUsersListener?): StringRequest {
            val url = "https://jsonplaceholder.typicode.com/users"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    listener?.onFetchAllUsersResponse(response)
                },
                { listener?.onError() })

            return stringRequest
        }
    }

    object Posts {
        interface FetchSinglePostListener {
            fun onFetchPostResponse(jsonString: String)
            fun onError()
        }

        interface FetchAllPostsListener {
            fun onFetchAllPostsResponse(jsonString: String)
            fun onError()
        }

        /**
         * Creates [StringRequest] that fetches one post with given id.
         * @param postId id of a post to fetch
         * @param listener listener used to pass fetched result to. Use [FetchSingleUserListener]
         * @return [StringRequest] to be used with [RequestQueueSingleton]
         */
        @JvmStatic
        fun fetchPost(postId: Int, listener: FetchSinglePostListener?): StringRequest {
            val url = "https://jsonplaceholder.typicode.com/posts/$postId"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    listener?.onFetchPostResponse(response)
                },
                { listener?.onError() })

            return stringRequest
        }

        /**
         * Creates [StringRequest] that fetches all users.
         * @param listener listener used to pass fetched result to. Use [FetchAllUsersListener]
         * @return [StringRequest] to be used with [RequestQueueSingleton]
         */
        @JvmStatic
        fun fetchPosts(listener: FetchAllPostsListener?): StringRequest {
            val url = "https://jsonplaceholder.typicode.com/posts"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    listener?.onFetchAllPostsResponse(response)
                },
                { listener?.onError() })

            return stringRequest
        }
    }

    object Photos {
        interface FetchSinglePhotoListener {
            fun onFetchPhotoResponse(jsonString: String)
            fun onError()
        }

        interface FetchAllPhotosListener {
            fun onFetchAllPhotosResponse(jsonString: String)
            fun onError()
        }

        @JvmStatic
        fun fetchPhoto(photoId: Int, listener: FetchSinglePhotoListener?): StringRequest {
            val url = "https://jsonplaceholder.typicode.com/photos/$photoId"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    listener?.onFetchPhotoResponse(response)
                },
                { listener?.onError() })

            return stringRequest
        }

        @JvmStatic
        fun fetchPhotos(listener: FetchAllPhotosListener?): StringRequest {
            val url = "https://jsonplaceholder.typicode.com/photos"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    listener?.onFetchAllPhotosResponse(response)
                },
                { listener?.onError() })

            return stringRequest
        }
    }
}