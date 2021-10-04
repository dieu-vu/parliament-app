package com.example.parliamentmemberapp.partyMemberList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.repository.MemberDataRepository

class PartyMemberViewModel(application: Application): AndroidViewModel(application) {


    private val database = MemberDatabase.getInstance(application)
    private val memberRepository = MemberDataRepository(database)

//    val partyMemberList: LiveData<List<MemberOfParliament>> = database.memberDatabaseDao.getPartyMemberList()
}