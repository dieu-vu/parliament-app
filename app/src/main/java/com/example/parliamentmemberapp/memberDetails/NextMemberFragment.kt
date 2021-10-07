package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import com.example.parliamentmemberapp.databinding.FragmentNextMemberBinding
import com.example.parliamentmemberapp.partyMemberList.PartyMemberFragmentArgs

class NextMemberFragment : Fragment() {


    private lateinit var viewModel: NextMemberViewModel
    private lateinit var binding: FragmentNextMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application

        val previousMemberData =
            NextMemberFragmentArgs.fromBundle(this.requireArguments()).previousMember


        // Inflate the layout for this fragment
        binding = FragmentNextMemberBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModelFactory = NextMemberViewModelFactory(previousMemberData, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NextMemberViewModel::class.java)
        binding.viewModel = viewModel

        updateMemberViewUI(viewModel)


        return binding.root
    }

    private fun updateMemberViewUI(viewModel: NextMemberViewModel) {
        viewModel.selectedMember.observe(viewLifecycleOwner, Observer { member ->
            binding.apply {
                name.text = viewModel?.updateNameText()
                constituency.text = viewModel?.updateConstituencyText()
                age.text = viewModel?.updateAgeText()
                party.text = viewModel?.updatePartyText()
                ifMinister.text = viewModel?.updateMemberTitle()
            }
        })
    }
}