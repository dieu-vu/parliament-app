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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartyListViewModel: ViewModel (){

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getParliamentProperties()
    }

    //Call the ParliamentApiService to enqueue the Retrofit request, implementing the callbacks
    private fun getParliamentProperties(){
        //Start network request on a background thread
        ParliamentApi.retrofitService.getProperties().enqueue(object: Callback<List<MemberOfParliament>> {
            override fun onResponse(call: Call<List<MemberOfParliament>>, response: Response<List<MemberOfParliament>>) {
                _response.value = "Success: ${response.body()?.size} members retrieved"
            }

            override fun onFailure(call: Call<List<MemberOfParliament>>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
        })
        _response.value = ""
    }
}