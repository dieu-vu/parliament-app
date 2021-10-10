//NAME: DIEU VU
//DATE CREATED: 9-10-2021

package com.example.parliamentmemberapp.searchMember

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parliamentmemberapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchString: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this


        searchString = "init not found"
        val viewModelFactory = SearchViewModelFactory(searchString, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        val adapter = SearchResultAdapter(SearchResultListener {
                member -> viewModel.onMemberNameClicked(member)
        })

        binding.searchViewModel = viewModel
        binding.searchResultList.adapter = adapter


        binding.searchBtn.setOnClickListener {
            requireView().hideKeyboard()
            searchString = binding.searchText.text.toString()
            viewModel.refreshSearchResult(searchString)
            binding.searchText.setText("")

            viewModel.searchResultList.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
                Log.i("ZZZ", "number of item in adapter: ${adapter.itemCount}")
            })

        }

        viewModel.navigateToMemberDetails.observe(viewLifecycleOwner, Observer{ member ->
            member?.let{
                this.findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToMemberFragment(member))
                viewModel.onMemberDetailsNavigationCompleted()
            }
        })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireView().hideKeyboard()
    }

    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}