//NAME: DIEU VU
//DATE CREATED: 8-10-2021

package com.example.parliamentmemberapp.data

import android.content.Context
import androidx.room.*

@Database(entities = [MemberFeedback::class], version =1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class MemberFeedbackDatabase: RoomDatabase() {

    abstract val memberFeedbackDao: MemberFeedbackDao

    companion object{
        @Volatile //changes made to variable INSTANCE are visible to other threads immediately
        private var INSTANCE: MemberFeedbackDatabase? = null

        fun getInstance(context: Context): MemberFeedbackDatabase{
            synchronized(this){
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


