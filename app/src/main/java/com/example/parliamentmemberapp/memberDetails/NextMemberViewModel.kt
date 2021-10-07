package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament


class NextMemberViewModel (previousMemberData: previousMemberData, application: Application):
    AndroidViewModel(application),
    MemberViewFormatting {

    private val database = MemberDatabase.getInstance(application)

    private var nextMember: LiveData<MemberOfParliament> = database.memberDatabaseDao.getNextMember(previousMemberData.partyName,previousMemberData.previousName)
    private val _selectedMember = nextMember as MutableLiveData<MemberOfParliament>
    override val selectedMember: LiveData<MemberOfParliament>
        get() =  _selectedMember

    fun onNextBtnClicked(member: MemberOfParliament) {
        nextMember = database.memberDatabaseDao.getNextMember(member.party,member.first)
    }

}

class NextMemberViewModelFactory(
    private val previousMemberData: previousMemberData,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NextMemberViewModel::class.java)) {
            return NextMemberViewModel(previousMemberData, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}