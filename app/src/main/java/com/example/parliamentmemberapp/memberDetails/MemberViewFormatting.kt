package com.example.parliamentmemberapp.memberDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parliamentmemberapp.data.MemberOfParliament
import java.util.*
interface MemberViewFormatting {

    val selectedMember: LiveData<MemberOfParliament>

    fun updateNameText(): String {
        return """${(selectedMember.value?.first) ?: "not found"} ${(selectedMember.value?.last) ?: "not found"}"""
    }

    fun updateConstituencyText(): String{
        return "Constituency: \n ${(selectedMember.value?.constituency)?: "not found"}"
    }

    fun updateAgeText(): String{
        val age: Int = (Calendar.getInstance().get(Calendar.YEAR) -
                (selectedMember.value?.bornYear ?: 0))
        return if (age < Calendar.getInstance().get(Calendar.YEAR)) "Age: \n $age" else "Age: \n not found"
    }

    fun updatePartyText(): String{
        return "Party: \n ${(selectedMember.value?.party)?: "not found"}"
    }

    //Display title if Minister or Member
    fun updateMemberTitle(): String{
        return if((selectedMember.value?.minister) == false) "Member of Parliament" else "Minister"

    }

}