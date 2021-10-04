package com.example.parliamentmemberapp.partyList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentPartyListBinding


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

        val adapter = PartyListAdapter(PartyListListener {
            party -> viewModel.onPartyNameClicked(party)
        })

        binding.partyList.adapter = adapter

        viewModel.navigateToPartyMemberList.observe(viewLifecycleOwner, Observer { party ->
            party?.let {
                view?.let { it ->
                    it.findNavController().navigate(PartyListFragmentDirections.
                    actionPartyListFragmentToPartyMemberFragment(party)
                    )
                }
                viewModel.displayPartyMemberListCompleted()
            }
        })

        viewModel.partyList.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.submitList(it) }
        })


        setHasOptionsMenu(true)
        return binding.root
    }


}