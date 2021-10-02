package com.example.parliamentmemberapp.partyList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.databinding.TextItemViewBinding

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class PartyListAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<String>()
    set(value) {
        field = value
        notifyDataSetChanged() // Tell Recycler not to draw old items
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }


}
