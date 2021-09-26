package com.example.parliamentmemberapp.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

// Retrofit Builder to create a Retrofit object
private const val BASE_URL = "https://users.metropolia.fi/~peterh/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//Implement the ParliamentApiService interface with getProperties returning a String from json file.
interface ParliamentApiService {
    @GET("mps.json")
    fun getProperties():
            Call<String>
}

//Create the ParliamentApi object using Retrofit to implement the ParliamentApiService
object ParliamentApi {
    val retrofitService: ParliamentApiService by lazy {
        retrofit.create(ParliamentApiService::class.java)
    }
}