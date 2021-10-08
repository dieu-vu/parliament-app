package com.example.parliamentmemberapp.title

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberFeedbackDatabase
import com.example.parliamentmemberapp.repository.MemberDataRepository
import com.example.parliamentmemberapp.repository.MemberFeedbackRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck

class TitleViewModel(application: Application): AndroidViewModel(application) {

    private val database = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)
    private lateinit var memberFeedbackDatabase: MemberFeedbackDatabase

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        coroutineScope.launch {
            memberRepository.refreshDatabase()

//Run only once when generating first time Member feedback database
//            memberFeedbackDatabase = MemberFeedbackDatabase.getInstance(application)
//            MemberFeedbackRepository(memberFeedbackDatabase).refreshFeedbackDatabase()

        }
    }
}