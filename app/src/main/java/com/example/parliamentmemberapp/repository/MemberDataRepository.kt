package com.example.parliamentmemberapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.parliamentmemberapp.MyApp
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.data.ParliamentApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberDataRepository (private val database: MemberDatabase) {

    private val dao = database.memberDatabaseDao

    val memberList: LiveData<List<MemberOfParliament>> = dao.getAllMembers()

    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
        val memberList = ParliamentApi.retrofitService.getProperties().await()
        dao.insertAll(memberList)
        }
    }

    suspend fun insertEntry(member: MemberOfParliament){
        dao.insert(member)
        Log.i( "ZZZ","added an entry")
    }


}