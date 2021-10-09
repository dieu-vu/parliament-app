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


    private var _memberFeedback = MutableLiveData<MemberFeedback>()
    override val memberFeedback: LiveData<MemberFeedback>
        get() = _memberFeedback

    init{
        Log.i("ZZZ", "MemberViewModel created!")
        _selectedMember.value = member
        viewModelScope.launch {
                _memberFeedback.value = _selectedMember.value?.personNumber?.let {
                    feedbackRepository.getMemberFeedback(it)
                }
            }
    }

    val imageSrcUrl: LiveData<String> = Transformations.map(selectedMember) { member -> "https://avoindata.eduskunta.fi/${member.picture}"}


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
            _memberFeedback.value = _selectedMember?.value?.let {
                feedbackRepository.getMemberFeedback(
                    it.personNumber)}
        }
    }

    fun updateFeedback(ratingChange: Int){
        viewModelScope.launch {
            val newMemberFeedback = _memberFeedback.value?.let { it ->
                MemberFeedback(
                it?.personNumber ?: 0,
                it?.rating.plus(ratingChange) ?: 0,
                it?.comment.toMutableList())
            }
            if (newMemberFeedback != null) {
                feedbackRepository.insertFeedback(newMemberFeedback)
                _memberFeedback.value = newMemberFeedback
            }
        }
    }


//Navigating to comment fragment, passing personNumber
    private val _navigateToComment = MutableLiveData<MemberFeedback>()
    val navigateToComment: LiveData<MemberFeedback>
        get() = _navigateToComment

    fun onCommentBtnClicked(memberFeedback: MemberFeedback) {
        _navigateToComment.value = memberFeedback
    }

    fun navigateToCommentCompleted() {
        _navigateToComment.value = null
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

