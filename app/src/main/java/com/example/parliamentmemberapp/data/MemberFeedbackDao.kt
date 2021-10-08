package com.example.parliamentmemberapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemberFeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedback(memberFeedback: MemberFeedback)

    @Query("SELECT * FROM member_feedback_db WHERE personNumber = :personNumber")
    fun getMemberFeedback(personNumber: Int): LiveData<MemberFeedback>

}