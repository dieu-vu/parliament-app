package com.example.parliamentmemberapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "member_feedback_db")
data class MemberFeedback(
    @PrimaryKey
    val personNumber: Int,
    @ColumnInfo (name = "rating")
    val rating: Int,
    @ColumnInfo (name = "comment")
    val comment: String
): Parcelable
