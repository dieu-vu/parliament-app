package com.example.parliamentmemberapp.data

import java.time.Year
import java.util.*

data class Member(
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String?,
    val bornYear: Int,
    val constituency: String){

    var age: Int = Year.now().getValue() - bornYear


    //TODO: Use the object ParliamentMembersData??

}
