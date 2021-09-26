package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MemberViewModel (
//    val database: MemberDatabaseDao,
//    application: Application
    ): ViewModel (){//AndroidViewModel(application){

    //private val members = database.getAllMembers()
    private val members = ParliamentMembersData.members
    private val _parliamentMember = MutableLiveData<MemberOfParliament>() //make a backing property
    val parliamentMember: LiveData<MemberOfParliament> //Encapsulate LiveData
        get() = _parliamentMember
    private var recentPickedIndex: Int? = null

//    val _response = MutableLiveData<String>()
//    val response: LiveData<String>
//        get() = _response

    init{
        Log.i("ViewModel", "MemberViewModel created!")
        _parliamentMember.value = getRandomMember() //get a random member from list without repetition
    }

    //Call the ParliamentApiService to enqueue the Retrofit request, implementing the callbacks
//    private fun getParliamentProperties(){
//        //Start network request on a background thread
//        ParliamentApi.retrofitService.getProperties().enqueue(object: Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                _response.value = response.body()
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//        })
//        _response.value = ""
//    }


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
//    fun getRandomMember(): MemberOfParliament? {
//        val next = members.value?.indices?.toSet()?.minus(setOfNotNull(recentPickedIndex))?.random()
//        recentPickedIndex = next
//        val pickedMember: MemberOfParliament? = next?.let { members.value?.get(it).also { _parliamentMember.value = it } }
//        return pickedMember
//    }
    fun getRandomMember(): MemberOfParliament {
        val next = members.indices.toSet().minus(setOfNotNull(recentPickedIndex)).random()
        recentPickedIndex = next
        val pickedMember: MemberOfParliament = next?.let { members.get(it).also { _parliamentMember.value = it } }
        return pickedMember
    }

    fun liked(){
        (_parliamentMember.value?.reactionPoints?: 0).plus(1)
    }

    fun disliked(){
        (_parliamentMember.value?.reactionPoints?: 0).minus(1)
    }


}