package com.example.parliamentmemberapp.repository

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

    suspend fun newEntry(){
        dao.insert(
            MemberOfParliament(
                personNumber = 1467,
                seatNumber = 64,
                last = "Huru",
                first = "Petri",
                party = "ps",
                minister = false,
                picture = "attachment/member/pictures/Huru-Petri-web-v0003-1467.jpg",
                twitter = "https://twitter.com/HuruPetri",
                bornYear = 1966,
                constituency = "Satakunta",
            )
        )
    }


}