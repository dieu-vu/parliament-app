package com.example.parliamentmemberapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parliamentmemberapp.MyApp
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.data.ParliamentApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberDataRepository (private val database: MemberDatabase) {

    private val dao = database.memberDatabaseDao

    val partyList: LiveData<List<String>> = dao.getPartyList()

    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            val memberList = ParliamentApi.retrofitService.getProperties().await()
            memberList.forEach { dao.insert(it) }
        }
    }

    suspend fun insertEntry(member: MemberOfParliament){
        dao.insert(member)
        Log.i( "ZZZ","added an entry")
    }

    fun getPartyMembers(partyName: String): LiveData<List<MemberOfParliament>> {
        return dao.getPartyMemberList(partyName)
    }


}