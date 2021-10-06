package com.example.parliamentmemberapp.partyMemberList

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.repository.MemberDataRepository

class PartyMemberViewModel(val partyName: String, application: Application): AndroidViewModel(application) {


    private val database = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    val partyMemberList: LiveData<List<MemberOfParliament>> = memberRepository.getPartyMembers(partyName)

    private val _navigateToMemberDetails = MutableLiveData<Int>()
    val navigateToMemberDetails
        get() = _navigateToMemberDetails

    fun onMemberNameClicked(personNumber: Int){
        _navigateToMemberDetails.value = personNumber
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