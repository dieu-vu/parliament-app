package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.MemberDatabaseDao
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.data.ParliamentMembersData
import java.util.*

class MemberViewModel (
//    val database: MemberDatabaseDao,
//    application: Application
    ): ViewModel() {//AndroidViewModel(application){

    private val _parliamentMember = MutableLiveData<MemberOfParliament>() //make a backing property
    val parliamentMember: LiveData<MemberOfParliament> //Encapsulate LiveData
        get() = _parliamentMember


    init{
        Log.i("ViewModel", "MemberViewModel created!")
        _parliamentMember.value = getRandomMember() //get a random member from list without repetition
    }

    override fun onCleared(){
        super.onCleared()
        Log.i("ViewModel", "MemberViewModel destroyed")
    }

    fun updateNameText(): String{
        return """${(_parliamentMember.value?.first) ?: "not found"} ${(_parliamentMember.value?.last) ?: "not found"}"""
    }

    fun updateConstituencyText(): String{
        return "Constituency: \n ${(_parliamentMember.value?.constituency)?: "not found"}"
    }

    fun updateAgeText(): String{
        val age: Int = (Calendar.getInstance().get(Calendar.YEAR) -
                (_parliamentMember.value?.bornYear ?: 0))
        return if (age < Calendar.getInstance().get(Calendar.YEAR)) "Age: \n $age" else "Age: \n not found"
    }

    fun updatePartyText(): String{
        return "Party: \n ${(_parliamentMember.value?.party)?: "not found"}"
    }

    //Display title if Minister or Member
    fun updateMemberTitle(): String{
        val title = if((_parliamentMember.value?.minister) == false) "Member of Parliament" else "Minister"
        return title
    }

    //Get random member non-repeatedly
    fun getRandomMember(): MemberOfParliament{
        var recent: Int? = null
        val next = (ParliamentMembersData.members).indices.toSet().minus(setOfNotNull(recent)).random()
        recent = next
        _parliamentMember.value = ParliamentMembersData.members[next]
        return ParliamentMembersData.members[next]
    }


}