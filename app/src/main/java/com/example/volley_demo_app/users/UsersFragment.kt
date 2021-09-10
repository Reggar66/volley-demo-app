package com.example.volley_demo_app.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.volley_demo_app.databinding.UsersFragmentBinding

class UsersFragment : Fragment() {
    private var _binding: UsersFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var usersAdapter: UsersAdapter
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UsersFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val button = binding.usersFragmentButton
        button.setOnClickListener {
            usersViewModel.fetchAllUsers()
        }

        usersAdapter = UsersAdapter()

        val recyclerView = binding.usersFragmentRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        usersViewModel.allUsers.observe(viewLifecycleOwner, { users: List<User>? ->
            if (users != null) {
                usersAdapter.updateData(users)
            }
        })
    }
}