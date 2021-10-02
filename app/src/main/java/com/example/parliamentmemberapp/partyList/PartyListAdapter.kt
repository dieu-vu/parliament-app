package com.example.parliamentmemberapp.partyList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.R


class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)


class PartyListAdapter: RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {

    var data = listOf<String>()
    set(value) {
        field = value
        notifyDataSetChanged() // Tell Recycler not to draw old items
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_party_list, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val partyName: TextView = itemView.findViewById(R.id.party_name)
        val partyLogo: ImageView = itemView.findViewById(R.id.party_logo)
    // Encapsulate binding on ViewHolder
        fun bind(
            item: String
        ) {
            val res = itemView.context.resources
            partyName.text = when (item) {
                "kd" -> "Christian Democrats"
                "kesk" -> "Centre Party"
                "kok" -> "National Coalition Party"
                "liik" -> "Movement Now"
                "ps" -> "Finns party"
                "r" -> "Swedish People's Party"
                "sd" -> "Social Democratic Party"
                "vas" -> "Left Alliance"
                "vihr" -> "Green League"
                else -> ""
            }

            partyLogo.setImageResource(
                when (item) {
                    "kd" -> R.drawable.kd
                    "kesk" -> R.drawable.kesk
                    "kok" -> R.drawable.kok
                    "liik" -> R.drawable.liik
                    "ps" -> R.drawable.ps
                    "r" -> R.drawable.rkp
                    "sd" -> R.drawable.sdp
                    "vas" -> R.drawable.vas
                    "vihr" -> R.drawable.vihr
                    else -> R.drawable.ic_launcher_foreground
                }
            )
        }
    }
}



