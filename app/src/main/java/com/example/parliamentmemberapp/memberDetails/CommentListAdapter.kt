package com.example.parliamentmemberapp.memberDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.R

class CommentListAdapter: RecyclerView.Adapter<TextItemViewHolder>(){
    var data = listOf<String>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item
        Log.i("ZZZ", "list item: ${item.toString()}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        Log.i("ZZZ","recycler view created for comment")
        return TextItemViewHolder(view)
    }

}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)