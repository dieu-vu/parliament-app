package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.*
import com.example.parliamentmemberapp.repository.MemberFeedbackRepository
import kotlinx.coroutines.launch


class MemberViewModel (member: MemberOfParliament, application: Application):
    AndroidViewModel(application),
    MemberViewFormatting {

    private val database = MemberDatabase.getInstance(application)

    private val feedbackDatabase = MemberFeedbackDatabase.getInstance(application)
    private val feedbackRepository = MemberFeedbackRepository(feedbackDatabase)

    private var _selectedMember = MutableLiveData<MemberOfParliament>()
    override val selectedMember: LiveData<MemberOfParliament>
        get() = _selectedMember


    init{
        Log.i("ZZZ", "MemberViewModel created!")
        _selectedMember.value = member
    }

    val imageSrcUrl: LiveData<String> = Transformations.map(selectedMember) { member -> "https://avoindata.eduskunta.fi/${member.picture}"}

    override val memberFeedback = _selectedMember?.value?.let {
            feedbackRepository.getMemberFeedback(
                it.personNumber)}

    fun getNextMemberData(){
        viewModelScope.launch {
            val nextMember = database.memberDatabaseDao.getNextMember(
                selectedMember.value?.party.toString(),
                selectedMember.value?.first.toString()
            )
            if (nextMember != null) {
                _selectedMember.value = nextMember
            } else {
                _selectedMember.value = database.memberDatabaseDao.getFirstMember(
                    selectedMember?.value?.party.toString())
            }
        }
    }

    fun updateFeedback(ratingChange: Int){
        viewModelScope.launch {
            val newMemberFeedback = MemberFeedback(
                memberFeedback?.value?.personNumber ?:0,
                memberFeedback?.value?.rating?.plus(ratingChange) ?:0,
                "${memberFeedback?.value?.comment}"
                )
            if (newMemberFeedback != null) {
                feedbackRepository.insertFeedback(newMemberFeedback)
            }
        }
    }
}



class MemberViewModelFactory(
    private val member: MemberOfParliament,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberViewModel::class.java)) {
            return MemberViewModel(member, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

