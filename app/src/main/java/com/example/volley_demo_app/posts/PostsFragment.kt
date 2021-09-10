package com.example.volley_demo_app.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.volley_demo_app.databinding.PostsFragmentBinding

class PostsFragment : Fragment() {

    private var _binding: PostsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostsViewModel by activityViewModels()

    private lateinit var postsAdapter: PostsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PostsFragmentBinding.inflate(inflater, container, false)


        postsAdapter = PostsAdapter()
        val recyclerView = binding.postsFragmentRecyclerView.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.fetchPosts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.posts.observe(viewLifecycleOwner, { posts: List<Post>? ->
            if (posts != null) {
                postsAdapter.updatePosts(posts)
            }
        })
    }
}