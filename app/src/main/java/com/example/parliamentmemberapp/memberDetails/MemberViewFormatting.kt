package com.example.parliamentmemberapp.memberDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.parliamentmemberapp.data.MemberFeedback
import com.example.parliamentmemberapp.data.MemberOfParliament
import java.util.*
interface MemberViewFormatting {

    val selectedMember: LiveData<MemberOfParliament>
    val memberFeedback: LiveData<MemberFeedback>?


    fun updateNameText(): String {
        val builder = StringBuilder()
        builder.append(selectedMember.value?.first)
        .append(" ")
        .append(selectedMember.value?.last)
        return builder.toString()
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
        val partyName = when (selectedMember.value?.party) {
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
        return "Party:\n ${partyName}"
    }

    //Display title if Minister or Member
    fun updateMemberTitle(): String{
        return if((selectedMember.value?.minister) == false) "Member of Parliament" else "Minister"
    }

    fun ratingScoreText(): String{
        return "Rating Score: ${memberFeedback?.value?.rating.toString()}"
    }

}