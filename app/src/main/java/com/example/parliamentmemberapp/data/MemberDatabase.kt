package com.example.parliamentmemberapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmemberapp.MyApp

@Database(entities = [MemberOfParliament::class], version =1, exportSchema = false)
abstract class MemberDatabase: RoomDatabase() {

    abstract val memberDatabaseDao: MemberDatabaseDao

    companion object{
        @Volatile //changes made to variable INSTANCE are visible to other threads immediately
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(context: Context): MemberDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemberDatabase::class.java,
                        "member_database"
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