package com.example.parliamentmemberapp.partyList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.MemberDatabaseDao

class PartyListViewModel (
    val database: MemberDatabaseDao,
    application: Application): AndroidViewModel(application){


    init {
        Log.i("PartyList View Model", "party name is")
    }
}