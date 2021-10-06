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

class MemberFragment : Fragment() {

    private lateinit var memberViewModel: MemberViewModel
    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment
        binding = FragmentMemberBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val member = MemberFragmentArgs.fromBundle(requireArguments()).selectedMember
        val viewModelFactory = MemberViewModelFactory(member, application)
        memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)
        binding.memberViewModel = memberViewModel

        updateMemberViewUI(memberViewModel)

        binding.viewOtherMember.setOnClickListener() {
            binding.invalidateAll()
            memberViewModel.selectRandomMember()
            updateMemberViewUI(memberViewModel)
        }
        return binding.root
    }

    private fun updateMemberViewUI(viewModel: MemberViewModel){
        memberViewModel.selectedMember.observe(viewLifecycleOwner, Observer { member ->
            binding.apply {
                name.text = memberViewModel?.updateNameText()
                constituency.text = memberViewModel?.updateConstituencyText()
                age.text = memberViewModel?.updateAgeText()
                party.text = memberViewModel?.updatePartyText()
                ifMinister.text = memberViewModel?.updateMemberTitle()
            }
        })
    }
}
