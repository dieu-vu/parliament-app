//NAME: DIEU VU
//DATE CREATED: 8-10-2021

package com.example.parliamentmemberapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity (tableName = "member_feedback_db")
@Parcelize //Parcelize this data object to pass as arguments between fragments
data class MemberFeedback(
    @PrimaryKey
    val personNumber: Int,
    @ColumnInfo (name = "rating")
    val rating: Int,
    @ColumnInfo (name = "comment")
    val comment: MutableList<String>
): Parcelable
