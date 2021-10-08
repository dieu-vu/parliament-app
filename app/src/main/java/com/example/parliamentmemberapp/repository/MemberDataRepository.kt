package com.example.parliamentmemberapp.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parliamentmemberapp.MyApp
import com.example.parliamentmemberapp.data.*

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

class MemberFeedbackRepository (private val feedbackDB: MemberFeedbackDatabase){
    private val feedbackDao = feedbackDB.memberFeedbackDao

    suspend fun generateFeedbackDatabase(){
        withContext(Dispatchers.IO){
            val memberList = ParliamentApi.retrofitService.getProperties().await()
            memberList.forEach { feedbackDao.update(MemberFeedback(it.personNumber, 0,"")) }
        }
    }

    suspend fun insertFeedback(member: MemberFeedback){
        feedbackDao.insert(member)
    }

    fun getMemberFeedback(personNumber: Int): LiveData<MemberFeedback>{
        return feedbackDao.getMemberFeedback(personNumber)
    }

//    fun getMemberRating(personNumber: Int): LiveData<Int>{
//        return feedbackDao.getMemberRating(personNumber)
//    }
//
//    fun getMemberComment(personNumber: Int): LiveData<List<String>>{
//        return feedbackDao.getMemberComment(personNumber)
//    }
}