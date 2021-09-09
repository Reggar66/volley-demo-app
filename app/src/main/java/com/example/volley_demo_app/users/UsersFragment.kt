package com.example.volley_demo_app.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.volley_demo_app.R

class UsersFragment : Fragment() {

    lateinit var text: TextView
    val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.users_fragment, container, false)

        text = rootView.findViewById(R.id.usersFragment_textView_text)

        val button = rootView.findViewById<Button>(R.id.usersFragment_button)
        button.setOnClickListener {
            usersViewModel.fetchAllUsers()
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        usersViewModel.allUsers.observe(viewLifecycleOwner, { users: List<User>? ->
            if (users != null) {
                var str = ""
                for (user in users) {
                    str = str.plus(user.toStringFormatted())
                }
                text.text = str
            }
        })
    }
}