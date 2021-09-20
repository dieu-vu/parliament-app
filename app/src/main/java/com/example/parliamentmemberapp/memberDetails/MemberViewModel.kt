package com.example.parliamentmemberapp.memberDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.data.ParliamentMembersData
import java.util.*

class MemberViewModel: ViewModel(){

    var parliamentMember = MutableLiveData<MemberOfParliament>()

    init{
        Log.i("ViewModel", "MemberViewModel created!")
        parliamentMember.value = (ParliamentMembersData.members).random()
    }

    /*TODO: Convert to LiveData
    - Each variable are updated separately, so the UI only updates exactly the ones which change.
    - Find out how to handle nullable most efficiently.
    */


    override fun onCleared(){
        super.onCleared()
        Log.i("ViewModel", "MemberViewModel destroyed")
    }

    fun updateNameText(): String{
        return """${(parliamentMember.value?.first) ?: "not found"} ${(parliamentMember.value?.last) ?: "not found"}"""
    }

    fun updateConstituencyText(): String{
        return "Constituency: \n ${(parliamentMember.value?.constituency)?: "not found"}"
    }

    fun updateAgeText(): String{
        val age = Calendar.getInstance().get(Calendar.YEAR) - (parliamentMember.value?.bornYear!!)
        return "Age: \n ${age}"
    }

    fun updatePartyText(): String{
        return "Party: \n ${(parliamentMember.value?.party)?: "not found"}"
    }

    fun updateMemberTitle(): String{
        val title = if((parliamentMember.value?.minister)?: false) "Minister" else "Member of Parliament"
        return "$title"
    }

    fun getRandomMember(){
        var recent: Int? = null
        val next = (ParliamentMembersData.members).indices.toSet().minus(setOfNotNull(recent)).random()
        recent = next
        parliamentMember.value = ParliamentMembersData.members[next]
    }


}