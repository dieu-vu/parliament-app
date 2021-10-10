//NAME: DIEU VU
//DATE CREATED: 4-10-2021

package com.example.parliamentmemberapp.partyMemberList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentPartyMemberBinding



class PartyMemberFragment : Fragment() {

    private lateinit var viewModel: PartyMemberViewModel
    private lateinit var viewModelFactory: PartyMemberViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPartyMemberBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        //partyName is passed from click on PartyList Fragment
        val partyName = PartyMemberFragmentArgs.fromBundle(this.requireArguments()).party

        //Create viewModel with passed PartyName variable
        viewModelFactory = PartyMemberViewModelFactory(partyName, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PartyMemberViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = PartyMemberListAdapter(PartyMemberListener {
            member -> viewModel.onMemberNameClicked(member)
        })

        // RecyclerView displaying Party's member list
        binding.partyMemberList.adapter = adapter

        viewModel.partyMemberList.observe(viewLifecycleOwner, {
            adapter.submitList(it) //Update the minimal changes in the list
        })

        viewModel.navigateToMemberDetails.observe(viewLifecycleOwner, Observer{ member ->
            member?.let{
                this.findNavController().navigate(
                    PartyMemberFragmentDirections.actionPartyMemberFragmentToMemberFragment(member))
                viewModel.onMemberDetailsNavigationCompleted()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }


    //Create option menu to navigate to search fragment from this fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }

}