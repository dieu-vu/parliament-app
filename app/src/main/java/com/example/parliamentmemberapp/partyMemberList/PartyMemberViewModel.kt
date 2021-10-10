//NAME: DIEU VU
//DATE CREATED: 4-10-2021

package com.example.parliamentmemberapp.partyMemberList

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.repository.MemberDataRepository

class PartyMemberViewModel(partyName: String, application: Application): AndroidViewModel(application) {


    private val database = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    val partyMemberList: LiveData<List<MemberOfParliament>> = memberRepository.getPartyMembers(partyName)

    private val _navigateToMemberDetails = MutableLiveData<MemberOfParliament>()
    val navigateToMemberDetails
        get() = _navigateToMemberDetails

    fun onMemberNameClicked(member: MemberOfParliament){
        _navigateToMemberDetails.value = member
    }

    //Set the live data to Null when the navigation is done
    fun onMemberDetailsNavigationCompleted(){
        _navigateToMemberDetails.value = null
    }

}


class PartyMemberViewModelFactory(
    private val partyName: String,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartyMemberViewModel::class.java)) {
            return PartyMemberViewModel(partyName, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}