package com.example.parliamentmemberapp.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MemberOfParliament (
    @PrimaryKey
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    @Json(name = "picture")
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
) : Parcelable

