package com.example.parliamentmemberapp.partyList

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmemberapp.MyApp
import com.example.parliamentmemberapp.data.MemberDatabase.Companion.getInstance
import com.example.parliamentmemberapp.data.ParliamentApi
import com.example.parliamentmemberapp.repository.MemberDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class PartyListViewModel(application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getInstance(application)
    private val memberRepository = MemberDataRepository(database)

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        viewModelScope.launch {
            //memberRepository.refreshDatabase()
            memberRepository.newEntry()
        }
    }
    val memberList = memberRepository.memberList.value
    val textDisplayed = memberList?.size.toString()


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