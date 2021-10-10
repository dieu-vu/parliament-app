//NAME: DIEU VU
//DATE CREATED: 18-9-2021


package com.example.parliamentmemberapp.memberDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.parliamentmemberapp.R
import com.example.parliamentmemberapp.data.MemberFeedback
import com.example.parliamentmemberapp.databinding.FragmentMemberBinding


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


        // Update UI with the selected member argument passed from previous fragment
        updateMemberViewUI()

        //Open Twitter link in the browser if the member has one:
        binding.twitterLink.setOnClickListener {
            memberViewModel.twitterUrl.observe(viewLifecycleOwner,{
                val i= Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(it))
                startActivity(i)
            })
        }

        // Update selected member livedata and UI on click button View next member
        binding.viewOtherMember.setOnClickListener {
            memberViewModel.getNextMemberData()
            updateMemberViewUI()
        }

        // Update member feedback data when user clicks upvote or downvote button
        binding.upVoteBtn.setOnClickListener {
            memberViewModel.updateFeedback(1 )
            updateMemberViewUI()
        }

        binding.downVoteBtn.setOnClickListener {
            memberViewModel.updateFeedback(-1 )
            updateMemberViewUI()
        }


        // Observe the selected member in order to update member's feedback data accordingly
        var observedMemberFeedback = MemberFeedback(member.personNumber,0, mutableListOf())
        memberViewModel.memberFeedback.observe(viewLifecycleOwner,  { observedMemberFeedback = it })


        // Update member feedback data when user add a comment
        binding.addComment.setOnClickListener {
            memberViewModel.onCommentBtnClicked(observedMemberFeedback)
        }


        //Navigate to Comment fragment to view and add comments
        memberViewModel.navigateToComment.observe(viewLifecycleOwner,  {
            nextMember ->
            nextMember?.let{
                this.findNavController().navigate(MemberFragmentDirections.actionMemberFragmentToCommentFragment(observedMemberFeedback))
                memberViewModel.navigateToCommentCompleted()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }


    //Function handle UI update with changes in Livedata of the selected Member:
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

        // Twitter logo is hidden if the member does not have a twitter link in the data
        memberViewModel.twitterUrl.observe(viewLifecycleOwner, {it ->
            if (it == "") {
                binding.twitterLink.visibility = GONE
            }
            else {
                binding.twitterLink.visibility = VISIBLE
            }
        })

        //Update live rating score
        memberViewModel.memberFeedback.observe(viewLifecycleOwner,  {
            binding.ratingScore.text = memberViewModel.ratingScoreText()
        })

    }

    //Create option menu to navigate to search fragment from this fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }
}
