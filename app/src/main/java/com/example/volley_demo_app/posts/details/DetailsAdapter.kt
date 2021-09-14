package com.example.volley_demo_app.posts.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.volley_demo_app.Photo
import com.example.volley_demo_app.R
import com.example.volley_demo_app.databinding.PostListItemBinding
import com.example.volley_demo_app.posts.Post
import com.example.volley_demo_app.users.User
import com.squareup.picasso.Picasso

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private var _binding: PostListItemBinding? = null
    private val binding get() = _binding!!

    private var post: Post? = null
    private var user: User? = null
    private var photo: Photo? = null

    private val comments = mutableListOf<Comment?>()

    class DetailsViewHolder(itemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val userImage = itemBinding.postListItemUserImage
        val fullName = itemBinding.postListItemTextViewName
        val userName = itemBinding.postListItemTextViewUsername
        val body = itemBinding.postListItemTextViewBody

        init {
            val root = itemBinding.root
            root.radius = 0f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = PostListItemBinding.inflate(inflater, parent, false)

        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        if (position == 0) {
            holder.userName.visibility = View.VISIBLE
            holder.userImage.visibility = View.VISIBLE

            holder.fullName.text = user?.name
            holder.userName.text = "@${user?.username}"
            holder.body.text = post?.body
            Picasso.get()
                .load(photo?.thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.userImage)
        } else {
            holder.userName.visibility = View.GONE
            holder.userImage.visibility = View.INVISIBLE

            holder.fullName.text = comments[position]?.email
            holder.body.text = comments[position]?.body
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun updateComments(comments: List<Comment>) {
        this.comments.clear()
        // setting first element as null since first one is post
        this.comments.add(0, null)
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }

    fun updatePost(post: Post?) {
        this.post = post
        // post is always at 0 position
        notifyItemChanged(0)
    }

    fun updateUser(user: User?) {
        this.user = user
        notifyItemChanged(0)
    }

    fun updatePhoto(photo: Photo?) {
        this.photo = photo
        notifyItemChanged(0)
    }
}
