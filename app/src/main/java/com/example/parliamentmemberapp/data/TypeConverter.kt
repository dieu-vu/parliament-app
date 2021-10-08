//NAME: DIEU VU
//DATE CREATED: 8-10-2021


package com.example.parliamentmemberapp.data

import androidx.room.TypeConverter

//Room does not support the ability to store Lists directly, nor the ability to convert to/from Lists.
//So we use this for multiple comments handling

class TypeConverter {

    @TypeConverter
    fun commentListToStoredString(commentList: MutableList<String>): String? {
        var storedString = ""
        for (comment in commentList){
            storedString += comment + ";"
        }
        return storedString
    }

    @TypeConverter
    fun storedStringToCommentList(storedString: String): List<String> {
        val commentList: List<String> = storedString.split(";").
                filter { it.isNotEmpty() }.toList()
        return commentList
    }
}