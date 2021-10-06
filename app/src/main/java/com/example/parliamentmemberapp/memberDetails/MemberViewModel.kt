package com.example.parliamentmemberapp.memberDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.parliamentmemberapp.data.*
import com.example.parliamentmemberapp.partyMemberList.PartyMemberViewModel
import com.example.parliamentmemberapp.repository.MemberDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MemberViewModel (member: MemberOfParliament, application: Application): AndroidViewModel(application){

    private val database = MemberDatabase.getInstance(application)


    private val _selectedMember = MutableLiveData<MemberOfParliament>()
    val selectedMember: LiveData<MemberOfParliament>
        get() = _selectedMember

    init{
        Log.i("ZZZ", "MemberViewModel created!")

        if (_selectedMember == null){
            selectRandomMember()
        }
        _selectedMember.value = member
    }

    val displayMemberTitle = Transformations.map(selectedMember){
        when(it.minister) {
            true -> "Minister"
            false -> "Parliament Member"
        }
    }

    fun selectRandomMember (){
        _selectedMember.value = database.memberDatabaseDao.getRandomMember().value
    }


    fun updateNameText(): String {
        return """${(_selectedMember.value?.first) ?: "not found"} ${(_selectedMember.value?.last) ?: "not found"}"""
    }

    fun updateConstituencyText(): String{
        return "Constituency: \n ${(_selectedMember.value?.constituency)?: "not found"}"
    }

    fun updateAgeText(): String{
        val age: Int = (Calendar.getInstance().get(Calendar.YEAR) -
                (_selectedMember.value?.bornYear ?: 0))
        return if (age < Calendar.getInstance().get(Calendar.YEAR)) "Age: \n $age" else "Age: \n not found"
    }

    fun updatePartyText(): String{
        return "Party: \n ${(_selectedMember.value?.party)?: "not found"}"
    }

    //Display title if Minister or Member
    fun updateMemberTitle(): String{
        return if((_selectedMember.value?.minister) == false) "Member of Parliament" else "Minister"

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