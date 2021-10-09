package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.TypeConverter
import com.example.parliamentmemberapp.databinding.FragmentCommentBinding


class CommentFragment : Fragment() {

    private lateinit var viewModel: CommentViewModel
    private lateinit var binding: FragmentCommentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val memberFeedback = CommentFragmentArgs.fromBundle(this.requireArguments()).memberFeedback

        binding = FragmentCommentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModelFactory = CommentViewModelFactory(memberFeedback, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CommentViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = CommentListAdapter()
        binding.commentList.adapter = adapter

        updateCommentListUI(adapter)

        binding.submitCommentBtn.setOnClickListener() {
            val newComment = binding.editComment.text.toString()
            viewModel.updateFeedback(newComment)
            updateCommentListUI(adapter)

        }

        return binding.root
    }

    private fun updateCommentListUI(adapter: CommentListAdapter){
        viewModel.memberFeedback.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it?.comment.toList().reversed()
            }
        })
    }


}