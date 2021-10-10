//NAME: DIEU VU
//DATE CREATED: 2-10-2021

package com.example.parliamentmemberapp.partyList

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase.Companion.getInstance
import com.example.parliamentmemberapp.repository.MemberDataRepository



class PartyListViewModel(application: Application): AndroidViewModel(application) {

    private val database = getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    //Party list from memberDatabaseDao method
    val partyList: LiveData<List<String>> = memberRepository.partyList

    //Navigation to PartyMemberList Fragment
    private val _navigateToPartyMemberList = MutableLiveData<String>()
    val navigateToPartyMemberList
        get() = _navigateToPartyMemberList

    fun onPartyNameClicked(party: String) {
        _navigateToPartyMemberList.value = party
    }

    fun displayPartyMemberListCompleted() {
        _navigateToPartyMemberList.value = null
    }


}