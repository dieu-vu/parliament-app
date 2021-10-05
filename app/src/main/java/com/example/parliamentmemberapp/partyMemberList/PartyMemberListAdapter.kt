package com.example.parliamentmemberapp.partyMemberList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.databinding.ListItemPartyMembersBinding

class PartyMemberListAdapter: ListAdapter<MemberOfParliament, PartyMemberListAdapter.ViewHolder>(PartyMemberListDiffCallBack()){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindMemberListItem(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createViewHolderFrom(parent)
    }

    class ViewHolder private constructor(val binding: ListItemPartyMembersBinding): RecyclerView.ViewHolder(binding.root){

        fun bindMemberListItem(
            item: MemberOfParliament
        ) {
            binding.memberName.text = "${item.first} ${item.last}"
            binding.memberTitle.text = if (item.minister) "Minister" else "Parliament Member"
            if (item.minister) {
                binding.memberTitle.setTextColor(Color.BLACK)
                binding.memberName.setTextColor(Color.BLACK)
            } else {
                binding.memberTitle.setTextColor(Color.GRAY)
                binding.memberName.setTextColor(Color.GRAY)
            }
        }

        companion object {
             fun createViewHolderFrom(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPartyMembersBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }


}

class PartyMemberListDiffCallBack: DiffUtil.ItemCallback<MemberOfParliament>(){
    override fun areItemsTheSame(
        oldItem: MemberOfParliament,
        newItem: MemberOfParliament
    ): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }

    override fun areContentsTheSame(
        oldItem: MemberOfParliament,
        newItem: MemberOfParliament
    ): Boolean {
        return (oldItem == newItem)
    }

}