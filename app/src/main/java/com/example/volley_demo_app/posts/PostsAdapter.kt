package com.example.volley_demo_app.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.volley_demo_app.data.Requests
import com.example.volley_demo_app.databinding.PostListItemBinding
import com.example.volley_demo_app.databinding.PostsFragmentBinding
import com.example.volley_demo_app.users.User

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.UserViewHolder>() {

    lateinit var context: Context
    private var _binding: PostListItemBinding? = null
    private val binding get() = _binding!!

    private val posts = mutableListOf<Post>()
    private val users = mutableMapOf<Int, User>()

    class UserViewHolder(itemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val text = itemBinding.postListItemTextView
        val userImage = itemBinding.postListItemUserImage
        val userFullName = itemBinding.postListItemTextViewName
        val userName = itemBinding.postListItemTextViewUsername
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        _binding = PostListItemBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.text.text = posts[position].body
        val user = users[posts[position].userId]
        holder.userFullName.text = user?.name
        holder.userName.text = "@${user?.username}"
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updatePosts(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        // TODO DiffUtilTool
        notifyDataSetChanged()
    }

    fun updateUsers(newUsers: Map<Int, User>) {
        users.clear()
        users.putAll(newUsers)
        // TODO DiffUtilTool
        notifyDataSetChanged()
    }
}
