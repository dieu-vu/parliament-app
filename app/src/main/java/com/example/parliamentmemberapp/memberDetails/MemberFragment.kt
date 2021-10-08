//NAME: DIEU VU
//DATE CREATED: 18-9-2021


package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding
import kotlinx.android.synthetic.main.fragment_member.*

//TODO: Add Up button and Menu

class MemberFragment : Fragment() {

    private lateinit var memberViewModel: MemberViewModel
    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val application = requireNotNull(activity).application

        binding = FragmentMemberBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val member = MemberFragmentArgs.fromBundle(requireArguments()).selectedMember
        val viewModelFactory = MemberViewModelFactory(member, application)
        memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)
        binding.memberViewModel = memberViewModel


        updateMemberViewUI(memberViewModel)

        binding.viewOtherMember.setOnClickListener() {
            Log.i("ZZZ", "Clicked button")
            memberViewModel.getNextMemberData()
            updateMemberViewUI(memberViewModel)
        }

        binding.upVoteBtn.setOnClickListener() {
            memberViewModel.updateFeedback(1 )
            updateMemberViewUI(memberViewModel)
        }

        binding.downVoteBtn.setOnClickListener() {
            memberViewModel.updateFeedback(-1 )
            updateMemberViewUI(memberViewModel)
        }


        return binding.root
    }

    private fun updateMemberViewUI(viewModel: MemberViewModel){
        memberViewModel.selectedMember.observe(viewLifecycleOwner, Observer { it ->
            binding.apply {
                name.text = memberViewModel?.updateNameText()
                constituency.text = memberViewModel?.updateConstituencyText()
                age.text = memberViewModel?.updateAgeText()
                party.text = memberViewModel?.updatePartyText()
                ifMinister.text = memberViewModel?.updateMemberTitle()
            }
        })
        memberViewModel.memberFeedback?.observe(viewLifecycleOwner, Observer {
            binding.ratingScore.text = memberViewModel?.ratingScoreText()
        })

    }
}
