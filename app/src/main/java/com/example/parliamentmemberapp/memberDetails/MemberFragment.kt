package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.data.MemberDatabase.Companion.getInstance
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import kotlinx.coroutines.InternalCoroutinesApi

//TODO: Add Up button and Menu

@InternalCoroutinesApi
class MemberFragment : Fragment() {

    private lateinit var memberViewModel: MemberViewModel
    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentMemberBinding>(inflater,
            R.layout.fragment_member, container, false)

//        val application = requireNotNull(this.activity).application
//
//        val dataSource = getInstance(application).memberDatabaseDao
//        val viewModelFactory = MemberViewModelFactory(dataSource, application)
//
//        val memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)
        memberViewModel = ViewModelProviders.of(this).get(MemberViewModel::class.java)
//
        binding.memberViewModel = memberViewModel
        binding.lifecycleOwner = this
//
        memberViewModel.parliamentMember.observe(this, Observer{newMember ->
            binding.apply {
                name.text = memberViewModel?.updateNameText()
                constituency.text = memberViewModel?.updateConstituencyText()
                age.text = memberViewModel?.updateAgeText()
                party.text = memberViewModel?.updatePartyText()
                ifMinister.text = memberViewModel?.updateMemberTitle()
            }
        })
        return binding.root
    }


}