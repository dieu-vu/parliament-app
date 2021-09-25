package com.example.parliamentmemberapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDatabaseDao {

    @Insert
    suspend fun insert(member: MemberOfParliament)

    @Update
    suspend fun update(member: MemberOfParliament)

    @Query("SELECT * FROM MemberOfParliament")
    fun getAllMembers(): LiveData<List<MemberOfParliament>>

    @Query("SELECT DISTINCT party FROM MemberOfParliament ORDER BY party")
    fun getPartyList(): LiveData<List<String>>

    @Query("SELECT * FROM MemberOfParliament WHERE party= :partyName")
    fun getPartyMemberList(partyName: String): LiveData<List<MemberOfParliament>>

    @Query("DELETE FROM MemberOfParliament")
    suspend fun clearData()

    //Query below is to use for the search function
    @Query("SELECT * FROM MemberOfParliament " +
            "WHERE first LIKE '%:searchString +%'" +
            "OR last LIKE '%:searchString%'"+
            "OR seatNumber == cast(:searchString AS INTEGER)"+
            "OR constituency LIKE '%:searchString%'"+
            "OR party LIKE '%:searchString%'"+
            "OR bornYear == cast(:searchString AS INTEGER)"
    )
    suspend fun searchMember(searchString: String): LiveData<List<MemberOfParliament>>

}