package com.example.volley_demo_app.posts.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.volley_demo_app.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    lateinit var adapter: DetailsAdapter

    private val viewModel: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        adapter = DetailsAdapter()

        val recyclerView = binding.detailsFragmentRecyclerView.apply {
            this.adapter = this@DetailsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        Log.v("INFO", "User id:${args.userId} | Post id:${args.postId}")

        viewModel.fetchPost(args.postId)
        viewModel.fetchComments(args.postId)
        viewModel.fetchUser(args.userId)
        viewModel.fetchPhoto(args.userId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.post.observe(viewLifecycleOwner, {
            adapter.updatePost(it)
        })
        viewModel.comments.observe(viewLifecycleOwner, {
            adapter.updateComments(it)
        })

        viewModel.user.observe(viewLifecycleOwner, {
            adapter.updateUser(it)
        })
        viewModel.photo.observe(viewLifecycleOwner, {
            adapter.updatePhoto(it)
        })
    }
}