package com.example.volley_demo_app.users

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.volley_demo_app.databinding.UserListItemBinding


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var _binding: UserListItemBinding? = null
    private val binding get() = _binding!!

    private val users = mutableListOf<User>()

    class ViewHolder(itemBinding: UserListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val name = itemBinding.userListItemTextViewName
        val username = itemBinding.userListItemTextViewUsername
        val email = itemBinding.userListItemTextViewEmail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        _binding = UserListItemBinding.inflate(inflater, parent, false)


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = users[position].name
        holder.username.text = users[position].username
        holder.email.text = users[position].email
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateData(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        // TODO DiffUtilTool
        notifyDataSetChanged()
    }
}
