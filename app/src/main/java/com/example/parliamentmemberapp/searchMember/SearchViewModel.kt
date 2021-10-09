package com.example.parliamentmemberapp.searchMember

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.partyMemberList.PartyMemberViewModel
import com.example.parliamentmemberapp.repository.MemberDataRepository
import kotlinx.coroutines.launch


class SearchViewModel(searchString: String, application: Application): AndroidViewModel(application) {

    private val database: MemberDatabase = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    var searchResultList: LiveData<List<MemberOfParliament>> = memberRepository.searchMembers(searchString)

    private val _navigateToMemberDetails = MutableLiveData<MemberOfParliament>()
    val navigateToMemberDetails
        get() = _navigateToMemberDetails


    fun refreshSearchResult(searchString: String){
        searchResultList = memberRepository.searchMembers(searchString)
    }

    fun onMemberNameClicked(member: MemberOfParliament){
        _navigateToMemberDetails.value = member
    }

    //Set the live data to Null when the navigation is done
    fun onMemberDetailsNavigationCompleted(){
        _navigateToMemberDetails.value = null
    }

}

class SearchViewModelFactory(
    private val searchString: String,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchString, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}