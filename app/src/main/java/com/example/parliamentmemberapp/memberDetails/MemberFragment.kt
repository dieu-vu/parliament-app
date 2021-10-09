//NAME: DIEU VU
//DATE CREATED: 18-9-2021


package com.example.parliamentmemberapp.memberDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parliamentmemberapp.data.MemberFeedback
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding

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


        updateMemberViewUI()

        binding.viewOtherMember.setOnClickListener {
            Log.i("ZZZ", "Clicked button")
            memberViewModel.getNextMemberData()
            updateMemberViewUI()
        }

        binding.upVoteBtn.setOnClickListener {
            memberViewModel.updateFeedback(1 )
            updateMemberViewUI()
        }

        binding.downVoteBtn.setOnClickListener {
            memberViewModel.updateFeedback(-1 )
            updateMemberViewUI()
        }


        var observedMemberFeedback = MemberFeedback(member.personNumber,0, mutableListOf())
        memberViewModel.memberFeedback.observe(viewLifecycleOwner,  { observedMemberFeedback = it })

        binding.addComment.setOnClickListener {
            memberViewModel.onCommentBtnClicked(observedMemberFeedback)
        }

        memberViewModel.navigateToComment.observe(viewLifecycleOwner,  {
            nextMember ->
            nextMember?.let{
                this.findNavController().navigate(MemberFragmentDirections.actionMemberFragmentToCommentFragment(observedMemberFeedback))
                memberViewModel.navigateToCommentCompleted()
            }
        })

        return binding.root
    }

    private fun updateMemberViewUI(){
        memberViewModel.selectedMember.observe(viewLifecycleOwner,  {
            binding.apply {
                name.text = memberViewModel?.updateNameText()
                constituency.text = memberViewModel?.updateConstituencyText()
                age.text = memberViewModel?.updateAgeText()
                party.text = memberViewModel?.updatePartyText()
                ifMinister.text = memberViewModel?.updateMemberTitle()
            }
        })
        memberViewModel.memberFeedback.observe(viewLifecycleOwner,  {
            binding.ratingScore.text = memberViewModel.ratingScoreText()
        })

    }
}
