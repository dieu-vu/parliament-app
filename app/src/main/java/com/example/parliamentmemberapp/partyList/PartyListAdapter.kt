package com.example.parliamentmemberapp.partyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.ListItemPartyListBinding


class PartyListAdapter: ListAdapter<String, PartyListAdapter.ViewHolder>(PartyListDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemPartyListBinding): RecyclerView.ViewHolder(binding.root) {

        // Encapsulate binding on ViewHolder
        fun bind(
            item: String
        ) {
            val res = itemView.context.resources
            binding.partyName.text = when (item) {
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

            binding.partyLogo.setImageResource(
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
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPartyListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PartyListDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}




