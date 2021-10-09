//NAME: DIEU VU
//DATE CREATED: 2-10-2021


package com.example.parliamentmemberapp.partyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.databinding.ListItemPartyListBinding


class PartyListAdapter(val clickListener: PartyListListener): ListAdapter<String, PartyListAdapter.ViewHolder>(PartyListDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemPartyListBinding): RecyclerView.ViewHolder(binding.root) {

        // Encapsulate binding on ViewHolder
        fun bind(item: String, clickListener: PartyListListener) {
            binding.party = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
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

// Pass a callback from the fragment to the view model when party name is clicked
class PartyListListener(val clickListener: (party: String) -> Unit){
    fun onClick(party: String) = clickListener(party)
}

