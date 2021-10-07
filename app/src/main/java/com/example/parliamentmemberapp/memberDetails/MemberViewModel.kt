package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.*
import androidx.room.PrimaryKey
import com.example.parliamentmemberapp.data.*
import com.example.parliamentmemberapp.partyMemberList.PartyMemberViewModel
import com.example.parliamentmemberapp.repository.MemberDataRepository
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MemberViewModel (member: MemberOfParliament, application: Application):
    AndroidViewModel(application),
    MemberViewFormatting {

    private val database = MemberDatabase.getInstance(application)
    private var _selectedMember = MutableLiveData<MemberOfParliament>()
    override val selectedMember: LiveData<MemberOfParliament>
        get() = _selectedMember


    init{
        Log.i("ZZZ", "MemberViewModel created!")
        _selectedMember.value = member
    }

    private val _navigateToNextMember = MutableLiveData<ArrayList<String>>()
    val navigateToNextMember
        get() = _navigateToNextMember


    fun getNextMemberData(){
        val nextMember = database.memberDatabaseDao.getNextMember(selectedMember?.value?.party.toString(),selectedMember?.value?.first.toString())
        Log.i("ZZZ", "next Member in getNextMemberData func : ${nextMember.toString()}")
        _selectedMember.postValue(nextMember.value)
        Log.i("ZZZ", "cai nay chac la luon null ${_selectedMember.value}")
        Log.i("ZZZ", "selected member after update to next member : ${_selectedMember.toString()}")
    }


    //For next member fragment test, but remove later
    fun onNextBtnClicked(member: MemberOfParliament) {
        _navigateToNextMember.value = arrayListOf(member.party, member.first)
    }

    fun navigateToNextMemberCompleted() {
        _navigateToNextMember.value = null
    }


}

class MemberViewModelFactory(
    private val member: MemberOfParliament,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("uncheck_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberViewModel::class.java)) {
            return MemberViewModel(member, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

@Parcelize
data class previousMemberData (
    val partyName: String,
    val previousName: String
): Parcelable
