package com.example.volley_demo_app.posts

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.volley_demo_app.Photo
import com.example.volley_demo_app.R
import com.example.volley_demo_app.data.Requests
import com.example.volley_demo_app.databinding.PostListItemBinding
import com.example.volley_demo_app.databinding.PostsFragmentBinding
import com.example.volley_demo_app.users.User
import com.squareup.picasso.Picasso

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.UserViewHolder>() {

    lateinit var context: Context
    private var _binding: PostListItemBinding? = null
    private val binding get() = _binding!!

    private val posts = mutableListOf<Post>()
    private val users = mutableMapOf<Int, User>()
    private val photos = mutableMapOf<Int, Photo>()

    var onClickListener: OnClickListener? = null

    fun interface OnClickListener {
        fun onClick(postId: Int, userId: Int)
    }


    inner class UserViewHolder(itemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val text = itemBinding.postListItemTextViewBody
        val userImage = itemBinding.postListItemUserImage
        val userFullName = itemBinding.postListItemTextViewName
        val userName = itemBinding.postListItemTextViewUsername
        val numberOfLikes = itemBinding.postListItemTextViewNumberOfLikes
        val numberOfReShares = itemBinding.postListItemTextViewNumberOfReShares
        val numberOfComments = itemBinding.postListItemTextViewNumberOfComments

        private val likeButton: ImageView = itemBinding.postListItemImageViewLikeButton
        private val commentsButton: ImageView = itemBinding.postListItemImageViewCommentsButton
        private val reShareButton: ImageView = itemBinding.postListItemImageViewReShareButton
        private val shareButton: ImageView = itemBinding.postListItemImageViewShareButton

        init {
            val root = itemBinding.root
            root.setOnClickListener {
                onClickListener?.onClick(posts[adapterPosition].id, posts[adapterPosition].userId)
            }

            commentsButton.setOnClickListener {
                onClickListener?.onClick(posts[adapterPosition].id, posts[adapterPosition].userId)
            }

            likeButton.setOnClickListener {
                val post = posts[adapterPosition]
                if (post.isLiked)
                    return@setOnClickListener

                post.likes += 1
                post.isLiked = true
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                notifyItemChanged(adapterPosition)
            }

            reShareButton.setOnClickListener {
                Toast.makeText(context, "This should pass post forward.", Toast.LENGTH_SHORT).show()
            }

            shareButton.setOnClickListener {
                Toast.makeText(context, "This should start sharing process.", Toast.LENGTH_SHORT).show()
            }
        }

        fun likeButtonHandler(post: Post) {
            if (post.isLiked){
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                likeButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_200))
            }
            else{
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                likeButton.imageTintList = null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        _binding = PostListItemBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val post = posts[position]
        val user = users[post.userId]

        holder.text.text = post.body
        holder.userFullName.text = user?.name
        holder.userName.text = "@${user?.username}"
        holder.numberOfLikes.text = post.likes.toString()
        holder.numberOfReShares.text = post.reShares.toString()
        holder.numberOfComments.text = post.comments.toString()

        val photo = photos[post.userId]
        Picasso.get().load(photo?.thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.userImage)

        holder.likeButtonHandler(post)
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

    fun updatePhotos(newPhotos: Map<Int, Photo>) {
        photos.clear()
        photos.putAll(newPhotos)
        // TODO DiffUtilTool
        notifyDataSetChanged()
    }
}
