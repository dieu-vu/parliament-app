package com.example.parliamentmemberapp.partyList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import com.example.parliamentmemberapp.databinding.FragmentPartyListBinding
import com.example.parliamentmemberapp.memberDetails.MemberViewModel


class PartyListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPartyListBinding.inflate(inflater)
        val viewModel = ViewModelProvider(this).get(PartyListViewModel::class.java)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the PartyListViewModel
        binding.viewModel = viewModel


        setHasOptionsMenu(true)
        return binding.root
    }


}