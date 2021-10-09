//NAME: DIEU VU
//DATE CREATED: 19-9-2021

package com.example.parliamentmemberapp.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import com.example.parliamentmemberapp.databinding.FragmentTitleBinding
import com.example.parliamentmemberapp.memberDetails.MemberViewModel
import com.example.parliamentmemberapp.partyList.PartyListViewModel


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

//        binding.viewInfoButton.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_memberFragment)
//        )
        binding.viewPartyList.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_partyListFragment)
        )
        return binding.root
    }


}