//NAME: DIEU VU
//DATE CREATED: 8-10-2021

package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberFeedback
import com.example.parliamentmemberapp.data.MemberFeedbackDatabase
import com.example.parliamentmemberapp.repository.MemberFeedbackRepository
import kotlinx.coroutines.launch

class CommentViewModel (memberFeedback: MemberFeedback, application: Application):
    AndroidViewModel(application){

    private val feedbackDatabase = MemberFeedbackDatabase.getInstance(application)
    private val feedbackRepository = MemberFeedbackRepository(feedbackDatabase)

    private var _memberFeedback = MutableLiveData<MemberFeedback>()
    val memberFeedback: LiveData<MemberFeedback>
        get() = _memberFeedback

    init {
        viewModelScope.launch {
            _memberFeedback.value = memberFeedback
        }
    }

    fun updateFeedback(newComment: String){
        viewModelScope.launch {
            val newMemberFeedback = _memberFeedback.value?.let {
                MemberFeedback(
                    it.personNumber,
                    it.rating,
                    it.comment.plus(newComment) as MutableList<String>
                )
            }
            if (newMemberFeedback != null) {
                feedbackRepository.insertFeedback(newMemberFeedback)
                _memberFeedback.value = newMemberFeedback
            }
        }
    }

}

class CommentViewModelFactory(
    private val memberFeedback: MemberFeedback,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(memberFeedback, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}