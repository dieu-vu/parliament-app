package com.example.parliamentmemberapp.data

import java.time.Year
import java.util.*

data class ParliamentMember(
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

    val age: String
        get() = (Year.now().getValue() - this.bornYear).toString()


    //TODO: Use the object ParliamentMembersData??

}
