//NAME: DIEU VU
//DATE CREATED: 2-10-2021

package com.example.parliamentmemberapp.partyList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }


}