package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.MemberOfParliament
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

        binding.apply {
            name.text = viewModel.updateNameText()
            constituency.text = viewModel.updateConstituencyText()
            age.text = viewModel.updateAgeText()
            party.text = viewModel.updatePartyText()
            ifMinister.text = viewModel.updateMemberTitle()
        }

        binding.viewOtherMember.setOnClickListener{ view: View ->
            onButtonClickedChangeData(view)
        }
        return binding.root
    }

    fun onButtonClickedChangeData(view: View){
        binding.invalidateAll()
    }


}