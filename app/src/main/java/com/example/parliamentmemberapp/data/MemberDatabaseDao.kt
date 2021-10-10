//NAME: DIEU VU
//DATE CREATED: 25-9-2021

package com.example.parliamentmemberapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: MemberOfParliament)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(member: MemberOfParliament)

    @Query("SELECT * FROM MemberOfParliament")
    fun getAllMembers(): LiveData<List<MemberOfParliament>>

    @Query("SELECT DISTINCT party FROM MemberOfParliament ORDER BY party")
    fun getPartyList(): LiveData<List<String>>

    @Query("SELECT * FROM MemberOfParliament WHERE party= :partyName ORDER BY first")
    fun getPartyMemberList(partyName: String): LiveData<List<MemberOfParliament>>

    @Query("SELECT * FROM MemberOfParliament WHERE party= :partyName AND first > :previousName ORDER BY first LIMIT 1")
    suspend fun getNextMember(partyName: String, previousName: String): MemberOfParliament

    @Query("SELECT * FROM MemberOfParliament WHERE party= :partyName ORDER BY first LIMIT 1")
    suspend fun getFirstMember(partyName: String): MemberOfParliament

    @Query("DELETE FROM MemberOfParliament")
    suspend fun clearData()

    @Query("""SELECT * FROM MemberOfParliament
            WHERE LOWER(first) LIKE LOWER('%' || :searchString || '%')
            OR LOWER(last) LIKE LOWER('%' || :searchString || '%')
            OR LOWER(constituency) LIKE LOWER('%' || :searchString || '%')
            OR LOWER(party) LIKE LOWER('%' || :searchString || '%')
            ORDER BY first"""
    )
    fun searchMember(searchString: String): LiveData<List<MemberOfParliament>>

}