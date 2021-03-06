//NAME: DIEU VU
//DATE CREATED: 8-10-2021


package com.example.parliamentmemberapp.memberDetails

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmemberapp.databinding.FragmentCommentBinding


class CommentFragment : Fragment() {

    private lateinit var viewModel: CommentViewModel
    private lateinit var binding: FragmentCommentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            requireView().hideKeyboard()
            val newComment = binding.editComment.text.toString()
            viewModel.updateFeedback(newComment)
            updateCommentListUI(adapter)
            binding.editComment.setText("")


        }
        return binding.root
    }

    private fun updateCommentListUI(adapter: CommentListAdapter){
        viewModel.memberFeedback.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it.comment.toList().reversed())
            }
        })
    }

    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }



}