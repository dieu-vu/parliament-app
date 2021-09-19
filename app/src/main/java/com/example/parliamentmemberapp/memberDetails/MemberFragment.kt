package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.ParliamentMember
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding


class MemberFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMemberBinding = DataBindingUtil.inflate<FragmentMemberBinding>(inflater,
            R.layout.fragment_member, container, false)
        val person1 = ParliamentMember(1467,64, "Huru","Petri", "ps",
        false, "attachment/member/pictures/Huru-Petri-web-v0003-1467.jpg","https://twitter.com/HuruPetri",
        1966, "Satakunta")
        binding.member = person1


    //TODO: Add function to display data -> to add ViewModel to handle this, fragment only takes care of View
        return binding.root
    }


}