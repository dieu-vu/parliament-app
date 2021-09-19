package com.example.parliamentmemberapp.memberDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.data.ParliamentMembersData
import java.util.*

class MemberViewModel: ViewModel(){

    private var parliamentMember: MemberOfParliament = (ParliamentMembersData.members).random()

    init{
        Log.i("ViewModel", "MemberViewModel created!")

    }

    override fun onCleared(){
        super.onCleared()
        Log.i("ViewModel", "MemberViewModel destroyed")
    }

    fun updateNameText(): String{
        return """${(parliamentMember?.first) ?: "not found"} ${(parliamentMember?.last) ?: "not found"}"""
    }

    fun updateConstituencyText(): String{
        return "Constituency: \n ${(parliamentMember?.constituency)?: "not found"}"
    }

    fun updateAgeText(): String{
        val age = Calendar.getInstance().get(Calendar.YEAR) - (parliamentMember?.bornYear)
        return "Age: \n ${age.toString()}"
    }

    fun updatePartyText(): String{
        return "Party: \n ${(parliamentMember?.party)?: "not found"}"
    }

    fun updateMemberTitle(): String{
        val title = if((parliamentMember?.minister)?: false) "Minister" else "Member of Parliament"
        return "$title"
    }

    fun getRandomMember(){
        var recent: Int? = null
        val next = (ParliamentMembersData.members).indices.toSet().minus(setOfNotNull(recent)).random()
        recent = next
        parliamentMember = ParliamentMembersData.members[next]
    }


}