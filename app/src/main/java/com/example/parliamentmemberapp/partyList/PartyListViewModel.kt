package com.example.parliamentmemberapp.partyList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.MemberDatabaseDao
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.data.ParliamentApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

class PartyListViewModel: ViewModel () {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getParliamentProperties()
    }

    //Call the ParliamentApiService to enqueue the Retrofit request, implementing the callbacks
    private fun getParliamentProperties(){
        //Start network request on a background thread
        coroutineScope.launch {
            var getMemberDataDeferred = ParliamentApi.retrofitService.getProperties()
            try{
                var listResult = getMemberDataDeferred.await()
                _response.value = "Success: get ${listResult.size} instances of class ${(listResult[0]::class.simpleName)}"
            } catch (e: Exception){
                    _response.value = "Failure: " + e.message
            }
        }

    }
}