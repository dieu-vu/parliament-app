//NAME: DIEU VU
//DATE CREATED: 8-10-2021


package com.example.parliamentmemberapp.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

//Room does not support the ability to store Lists directly, nor the ability to convert to/from Lists.
//So we use this Type converter for multiple comments handling

class TypeConverter {

    @TypeConverter
    fun convertCommentsToString(commentList: MutableList<String>): String {
        return commentListToStoredString(commentList)
    }
    @TypeConverter
    fun convertStringToComments(storedString: String): List<String> {
        return storedStringToCommentList(storedString)
    }


    companion object {
        @TypeConverter
        fun commentListToStoredString(commentList: MutableList<String>): String {
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


}