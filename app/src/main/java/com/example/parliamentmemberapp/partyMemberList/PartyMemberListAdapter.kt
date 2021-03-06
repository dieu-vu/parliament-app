//NAME: DIEU VU
//DATE CREATED: 4-10-2021

package com.example.parliamentmemberapp.partyMemberList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.databinding.ListItemPartyMembersBinding

class PartyMemberListAdapter (val clickListener: PartyMemberListener): ListAdapter<MemberOfParliament, PartyMemberListAdapter.ViewHolder>(PartyMemberListDiffCallBack()){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindMemberListItem(item, clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createViewHolderFrom(parent)
    }

    class ViewHolder private constructor(val binding: ListItemPartyMembersBinding): RecyclerView.ViewHolder(binding.root){

        fun bindMemberListItem(
            item: MemberOfParliament,
            clickListener: PartyMemberListener
        ) {
            binding.member = item //bind member variable to item at the position
            binding.clickListener = clickListener
            binding.executePendingBindings()

            //Binding text view
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

//Update List item with the changes only
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

class PartyMemberListener(val clickListener: (member: MemberOfParliament) -> Unit){
    fun onClick(member: MemberOfParliament) = clickListener(member)
}