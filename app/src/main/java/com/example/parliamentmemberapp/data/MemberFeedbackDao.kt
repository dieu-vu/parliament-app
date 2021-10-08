package com.example.parliamentmemberapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberFeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memberFeedback: MemberFeedback)

    @Update
    suspend fun update(memberFeedback: MemberFeedback)

    @Query("SELECT * FROM member_feedback_db WHERE personNumber = :personNumber")
    suspend fun getMemberFeedback(personNumber: Int): MemberFeedback

    @Query("DELETE FROM member_feedback_db")
    fun clear()


}