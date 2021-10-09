package com.example.parliamentmemberapp.data

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.room.TypeConverter
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.parliamentmemberapp.repository.MemberFeedbackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Database(entities = [MemberFeedback::class], version =1, exportSchema = false)
@TypeConverters(com.example.parliamentmemberapp.data.TypeConverter::class)
abstract class MemberFeedbackDatabase: RoomDatabase() {

    abstract val memberFeedbackDao: MemberFeedbackDao

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


