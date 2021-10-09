//NAME: DIEU VU
//DATE CREATED: 9-10-2021

package com.example.parliamentmemberapp.searchMember

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.databinding.ResultItemViewBinding


class SearchResultAdapter (val clickListener: SearchResultListener): ListAdapter<MemberOfParliament, SearchResultAdapter.ViewHolder>(SearchResultListDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindResultListItem(item, clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createViewHolderFrom(parent)
    }

    class ViewHolder private constructor(val binding: ResultItemViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bindResultListItem(
            item: MemberOfParliament,
            clickListener: SearchResultListener
        ) {
            binding.member = item
            binding.itemContent.text = formatResultText(item)
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }
        fun formatResultText(item: MemberOfParliament): String {
            val builder = StringBuilder()
            builder
                .append(item.first)
                .append(" ")
                .append(item.last)
                .append(" - ")
                .append(item.party)
            return builder.toString()
        }

        companion object {
            fun createViewHolderFrom(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ResultItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class SearchResultListDiffCallBack: DiffUtil.ItemCallback<MemberOfParliament>(){
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

class SearchResultListener(val clickListener: (member: MemberOfParliament) -> Unit){
    fun onClick(member: MemberOfParliament) = clickListener(member)
}