//NAME: DIEU VU
//DATE CREATED: 19-9-2021

package com.example.parliamentmemberapp.title

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.parliamentmemberapp.MyApp
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberFeedbackDatabase
import com.example.parliamentmemberapp.repository.MemberDataRepository
import com.example.parliamentmemberapp.repository.MemberFeedbackRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TitleViewModel(application: Application): AndroidViewModel(application) {

    private val database = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)
    private lateinit var memberFeedbackDatabase: MemberFeedbackDatabase

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        coroutineScope.launch {
            memberRepository.refreshDatabase()

            //Check if memberFeedback database exist, if not prepopulate a new one
            if (MyApp.appContext.getDatabasePath("member_feedback_db").exists() == false) {
                memberFeedbackDatabase = MemberFeedbackDatabase.getInstance(application)
                MemberFeedbackRepository(memberFeedbackDatabase).refreshFeedbackDatabase()
            }

        }
    }
}