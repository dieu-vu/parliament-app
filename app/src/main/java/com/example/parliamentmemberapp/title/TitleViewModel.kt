package com.example.parliamentmemberapp.title

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.repository.MemberDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TitleViewModel(application: Application): AndroidViewModel(application) {

    private val database = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        coroutineScope.launch {
            memberRepository.refreshDatabase()
        }
    }
}