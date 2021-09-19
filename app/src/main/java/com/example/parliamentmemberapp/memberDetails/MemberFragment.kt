package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.ParliamentMember
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding


class MemberFragment : Fragment() {

    private lateinit var viewModel: MemberViewModel
    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentMemberBinding>(inflater,
            R.layout.fragment_member, container, false)

        viewModel = ViewModelProviders.of(this).get(MemberViewModel::class.java)

        binding.member = viewModel.parliamentMember
        binding.apply {
            firstName.text = viewModel.updateFirstNameText(member)
            lastName.text = viewModel.updateLastNameText(member)
            constituency.text = viewModel.updateConstituencyText(member)
            age.text = viewModel.updateAgeText(member)
            party.text = viewModel.updatePartyText(member)
            ifMinister.text = viewModel.updateMemberTitle(member)
        }


    //TODO: Add function to display data -> to add ViewModel to handle this, fragment only takes care of View
        return binding.root
    }


}