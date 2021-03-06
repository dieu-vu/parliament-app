//NAME: DIEU VU
//DATE CREATED: 25-9-2021

package com.example.parliamentmemberapp.data

import android.content.Context
import android.util.Log
import androidx.room.*

@Database(entities = [MemberOfParliament::class], version =1, exportSchema = false)
@TypeConverters(TypeConverter::class)
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