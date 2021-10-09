//NAME: DIEU VU
//DATE CREATED: 9-10-2021

package com.example.parliamentmemberapp.memberDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.TextItemViewBinding

class CommentListAdapter: ListAdapter<String, CommentListAdapter.TextItemViewHolder>(CommentDiffCallback()){


    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }

    class TextItemViewHolder private constructor(val binding: TextItemViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: String){
            binding.singleComment.text = item
        }

        companion object {
            fun from(parent: ViewGroup): TextItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextItemViewBinding.inflate(layoutInflater, parent, false)
                return TextItemViewHolder(binding)
            }
        }
    }


}

class CommentDiffCallback: DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

