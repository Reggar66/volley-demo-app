package com.example.volley_demo_app.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.volley_demo_app.databinding.PostListItemBinding
import com.example.volley_demo_app.databinding.PostsFragmentBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.UserViewHolder>() {

    lateinit var context: Context
    private var _binding: PostListItemBinding? = null
    private val binding get() = _binding!!

    private val posts = mutableListOf<Post>()

    class UserViewHolder(itemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val text = itemBinding.postListItemTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        _binding = PostListItemBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.text.text = posts[position].body
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
}
