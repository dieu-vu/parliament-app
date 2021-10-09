//NAME: DIEU VU
//DATE CREATED: 2-10-2021

package com.example.parliamentmemberapp.repository


import android.util.Log
import androidx.lifecycle.LiveData
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

    fun getPartyMembers(partyName: String): LiveData<List<MemberOfParliament>> {
        return dao.getPartyMemberList(partyName)
    }

    fun searchMembers(searchString: String): LiveData<List<MemberOfParliament>> {
        Log.i("ZZZ", "founded: ${dao.searchMember(searchString).value?.size}")
        return dao.searchMember(searchString)
    }
}

class MemberFeedbackRepository (private val feedbackDB: MemberFeedbackDatabase){
    private val feedbackDao = feedbackDB.memberFeedbackDao

    //Use only to prepopulate the MemberFeedback data on first run
    suspend fun refreshFeedbackDatabase(){
        withContext(Dispatchers.IO){
            val memberList = ParliamentApi.retrofitService.getProperties().await()
            memberList.forEach { feedbackDao.update(MemberFeedback(it.personNumber, 0, mutableListOf())) }
        }
    }


    suspend fun insertFeedback(member: MemberFeedback){
        feedbackDao.insert(member)
    }

    suspend fun getMemberFeedback(personNumber: Int): MemberFeedback{
        return feedbackDao.getMemberFeedback(personNumber)
    }


}