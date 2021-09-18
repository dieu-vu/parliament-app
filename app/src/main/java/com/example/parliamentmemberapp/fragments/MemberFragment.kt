package com.example.parliamentmemberapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.Member
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding


class MemberFragment : Fragment() {

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMemberBinding = DataBindingUtil.inflate<FragmentMemberBinding>(inflater,
            R.layout.fragment_member, container, false)
        val person1 = Member(1467,64, "Huru","Petri", "ps",
        false, "attachment/member/pictures/Huru-Petri-web-v0003-1467.jpg","https://twitter.com/HuruPetri",
        1966, "Satakunta")
        binding.member = person1

    //TODO: Add function to display data
        return binding.root
    }


}