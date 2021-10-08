package com.example.parliamentmemberapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "member_feedback_db")
data class MemberFeedback(
    @PrimaryKey
    val personNumber: Int,
    val rating: Int,
    val comment: String
)
