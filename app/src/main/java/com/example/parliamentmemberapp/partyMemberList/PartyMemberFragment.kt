package com.example.parliamentmemberapp.partyMemberList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.MemberOfParliament
import com.example.parliamentmemberapp.databinding.FragmentPartyMemberBinding
import com.example.parliamentmemberapp.partyList.PartyListViewModel


class PartyMemberFragment : Fragment() {

    private lateinit var viewModel: PartyMemberViewModel
    private lateinit var viewModelFactory: PartyMemberViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPartyMemberBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        val partyName = PartyMemberFragmentArgs.fromBundle(this.requireArguments()).party

        viewModelFactory = PartyMemberViewModelFactory(partyName, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PartyMemberViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = PartyMemberListAdapter(PartyMemberListener {
            personNumber -> viewModel.onMemberNameClicked(personNumber)
        })

        binding.partyMemberList.adapter = adapter


        viewModel.partyMemberList.observe(viewLifecycleOwner, {
            adapter.submitList(it) //Update the minimal changes in the list
        })

        viewModel.navigateToMemberDetails.observe(viewLifecycleOwner, Observer{ member ->
            member?.let{
                this.findNavController().navigate(
                    PartyMemberFragmentDirections.actionPartyMemberFragmentToMemberFragment()) //TODO: Add arg (value passed to Member details fragment) here!
                viewModel.onMemberDetailsNavigationCompleted()
            }
        })

        return binding.root
    }


}