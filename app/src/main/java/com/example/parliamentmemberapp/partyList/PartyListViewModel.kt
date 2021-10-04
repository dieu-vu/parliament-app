package com.example.parliamentmemberapp.partyList

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase.Companion.getInstance
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import com.example.parliamentmemberapp.memberDetails.MemberViewModel
import com.example.parliamentmemberapp.repository.MemberDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class PartyListViewModel(application: Application): AndroidViewModel(application) {

    private val database = getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    val partyList: LiveData<List<String>> = memberRepository.partyList

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