//NAME: DIEU VU
//DATE CREATED: 19-9-2021

package com.example.parliamentmemberapp.title

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentTitleBinding



class TitleFragment : Fragment() {

    private lateinit var titleViewModel: TitleViewModel
    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        titleViewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title, container, false)

        binding.searchBtn.setOnClickListener {
            this.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToSearchFragment())
        }

        binding.viewPartyList.setOnClickListener {
            this.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToPartyListFragment())
        }

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