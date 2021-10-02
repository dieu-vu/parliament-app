package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberDatabaseDao

class MemberViewModelFactory
    (private val dataSource: MemberDatabase,
     private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberViewModelFactory::class.java)) {
            return MemberViewModelFactory(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}