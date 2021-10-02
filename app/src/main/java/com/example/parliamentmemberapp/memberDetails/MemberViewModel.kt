package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.*
import com.example.parliamentmemberapp.repository.MemberDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MemberViewModel (application: Application): AndroidViewModel(application){

    private val database = MemberDatabase.getInstance(application)

    lateinit var parliamentMember: LiveData<MemberOfParliament>


    init{
        Log.i("ZZZ", "MemberViewModel created!")
        selectNewMember()
    }

    fun selectNewMember (){
        parliamentMember = database.memberDatabaseDao.getRandomMember()
    }


    fun updateNameText(): String {
        return """${(parliamentMember.value?.first) ?: "not found"} ${(parliamentMember.value?.last) ?: "not found"}"""
    }

    fun updateConstituencyText(): String{
        return "Constituency: \n ${(parliamentMember.value?.constituency)?: "not found"}"
    }

    fun updateAgeText(): String{
        val age: Int = (Calendar.getInstance().get(Calendar.YEAR) -
                (parliamentMember.value?.bornYear ?: 0))
        return if (age < Calendar.getInstance().get(Calendar.YEAR)) "Age: \n $age" else "Age: \n not found"
    }

    fun updatePartyText(): String{
        return "Party: \n ${(parliamentMember.value?.party)?: "not found"}"
    }

    //Display title if Minister or Member
    fun updateMemberTitle(): String{
        return if((parliamentMember.value?.minister) == false) "Member of Parliament" else "Minister"

    }


}