package com.example.parliamentmemberapp.partyMemberList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentPartyMemberBinding
import com.example.parliamentmemberapp.partyList.PartyListViewModel


class PartyMemberFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application



        val partyMemberList = PartyMemberFragmentArgs.fromBundle(this.requireArguments()).party

        val viewModel = ViewModelProvider(this).get(PartyMemberViewModel::class.java)

        val binding = FragmentPartyMemberBinding.inflate(inflater)
        binding.partyMemberText.text = partyMemberList

        return binding.root
    }


}