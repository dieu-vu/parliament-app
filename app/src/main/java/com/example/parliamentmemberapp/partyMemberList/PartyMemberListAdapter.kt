package com.example.parliamentmemberapp.partyMemberList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.MemberOfParliament

class PartyMemberListAdapter: RecyclerView.Adapter<TextItemViewHolder>(){
    var data = listOf<MemberOfParliament>()
    set (value){
        field=value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = "${item.first} ${item.last}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }


}





class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)