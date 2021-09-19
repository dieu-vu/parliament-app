package com.example.parliamentmemberapp.memberDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.parliamentmemberapp.data.ParliamentMember

class MemberViewModel: ViewModel(){
    init{
        Log.i("ViewModel", "MemberViewModel created!")
    }

    override fun onCleared(){
        super.onCleared()
        Log.i("ViewModel", "MemberViewModel destroyed")
    }
    val person1 = ParliamentMember(1467,64, "Huru","Petri", "ps",
        false, "attachment/member/pictures/Huru-Petri-web-v0003-1467.jpg","https://twitter.com/HuruPetri",
        1966, "Satakunta")
}