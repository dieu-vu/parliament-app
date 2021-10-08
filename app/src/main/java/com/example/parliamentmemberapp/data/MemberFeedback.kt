package com.example.parliamentmemberapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "member_feedback_db")
data class MemberFeedback(
    @PrimaryKey
    val personNumber: Int,
    @ColumnInfo (name = "rating")
    val rating: Int,
    @ColumnInfo (name = "comment")
    val comment: String
)
