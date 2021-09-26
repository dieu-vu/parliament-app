package com.example.parliamentmemberapp.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title, container, false)
        binding.viewInfoButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_memberFragment)
        )
        binding.viewPartyList.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_partyListFragment)
        )
        return binding.root
    }


}