package com.example.parliamentmemberapp.partyList

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.MemberDatabase.Companion.getInstance
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import com.example.parliamentmemberapp.memberDetails.MemberViewModel
import com.example.parliamentmemberapp.repository.MemberDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class PartyListViewModel(application: Application): AndroidViewModel(application) {


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getInstance(application)
    private val memberRepository = MemberDataRepository(database)

//    private val _response = MutableLiveData<String>()
//    val response: LiveData<String>
//        get() = _response


    val partyList: LiveData<List<String>> = memberRepository.partyList



    }