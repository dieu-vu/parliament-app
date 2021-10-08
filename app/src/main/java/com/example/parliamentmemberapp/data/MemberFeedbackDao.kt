package com.example.parliamentmemberapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberFeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memberFeedback: MemberFeedback)

    @Update
    suspend fun update(memberFeedback: MemberFeedback)

//    @Query("SELECT rating FROM member_feedback_db WHERE personNumber = :personNumber")
//    fun getMemberRating(personNumber: Int): LiveData<Int>
//
//    @Query("SELECT comment FROM member_feedback_db WHERE personNumber = :personNumber")
//    fun getMemberComment(personNumber: Int): LiveData<List<String>>

    @Query("SELECT * FROM member_feedback_db WHERE personNumber = :personNumber")
    fun getMemberFeedback(personNumber: Int): LiveData<MemberFeedback>

    @Query("DELETE FROM member_feedback_db")
    fun clear()
}