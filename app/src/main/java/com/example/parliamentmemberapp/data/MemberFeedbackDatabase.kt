package com.example.parliamentmemberapp.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class MemberFeedbackDatabase: RoomDatabase() {

    abstract val memberDatabaseDao: MemberDatabaseDao

    companion object{
        @Volatile //changes made to variable INSTANCE are visible to other threads immediately
        private var INSTANCE: MemberFeedbackDatabase? = null

        fun getInstance(context: Context): MemberFeedbackDatabase{
            synchronized(this){
                Log.i("ZZZ", "DB onCreate")
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemberFeedbackDatabase::class.java,
                        "member_feedback_db"
                    )
                        .fallbackToDestructiveMigration() //For now sweeping data instead of creating new Migration object
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}