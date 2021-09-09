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
}