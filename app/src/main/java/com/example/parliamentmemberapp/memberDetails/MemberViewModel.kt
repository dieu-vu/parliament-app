package com.example.parliamentmemberapp.memberDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.ParliamentMember

class MemberViewModel: ViewModel(){

    val parliamentMember = ParliamentMember(1467,64, "Huru","Petri", "ps",
        false, "attachment/member/pictures/Huru-Petri-web-v0003-1467.jpg","https://twitter.com/HuruPetri",
        1966, "Satakunta")

    init{
        Log.i("ViewModel", "MemberViewModel created!")
    }

    override fun onCleared(){
        super.onCleared()
        Log.i("ViewModel", "MemberViewModel destroyed")
    }

    fun updateFirstNameText(parliamentMember: ParliamentMember?): String{
        return "First Name: \n ${(parliamentMember?.first)?: "not found"}"
    }

    fun updateLastNameText(parliamentMember: ParliamentMember?): String{
        return "Last Name: \n ${(parliamentMember?.last)?: "not found"}"
    }

    fun updateConstituencyText(parliamentMember: ParliamentMember?): String{
        return "Constituency: \n ${(parliamentMember?.constituency)?: "not found"}"
    }

    fun updateAgeText(parliamentMember: ParliamentMember?): String{
        return "Age: \n ${(parliamentMember?.age)?: "not found"}"
    }

    fun updatePartyText(parliamentMember: ParliamentMember?): String{
        return "Party: \n ${(parliamentMember?.party)?: "not found"}"
    }

    fun updateMemberTitle(parliamentMember: ParliamentMember?): String{
        val title = if((parliamentMember?.minister)?: false) "Minister" else "Member of Parliament"
        return "$title"
    }


}