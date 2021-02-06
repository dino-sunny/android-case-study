package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products

import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.utilities.NetworkCheck


class DealListFragment : Fragment() {
  private lateinit var binding : FragmentDealListBinding
  private lateinit var viewModel: DealListViewModel
private lateinit var dealsAdapter: DealItemAdapter

  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View {
    binding = FragmentDealListBinding.inflate(inflater,container,false)
    viewModel = ViewModelProvider(this).get(DealListViewModel::class.java)
    binding.viewModel = viewModel

    setListAdapter()
    setObservers()

    return binding.root
  }

  override fun onStart() {
    super.onStart()
    getDeals()
  }

  //Get the posts if network connection available else .
  //network error message
  private fun getDeals() {
    if (NetworkCheck.isOnline(requireContext())) {
//      noInternetLayout.visibility = View.GONE
      viewModel.getDeals()
    }
//    else{
//      noInternetLayout.visibility = View.VISIBLE
//    }
  }


  private fun setObservers() {
    //Navigate action handling
    viewModel.eventNavigateDeal.observe(viewLifecycleOwner) { post ->
      post?.let { navigateToDetails(post)}
    }
    viewModel.responsePosts.observe(viewLifecycleOwner) {Products->
      Products?.let { list ->
        if (!list.products.isNullOrEmpty()) {
          dealsAdapter.submitList(Products.products)
        }
      }
    }
  }

  private fun setListAdapter() {
    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    dealsAdapter = DealItemAdapter(DealItemListener {deal: DealItem ->
      viewModel.onDealClicked(deal)
    })
    binding.recyclerView.adapter = dealsAdapter
  }
  //Navigate to Details with post data
  private fun navigateToDetails(deal: DealItem) {
    val mData = Gson().toJson(deal)
    findNavController().navigate(
            DealListFragmentDirections
                    .actionDealListFragmentToDealItemFragment(mData)
    )
    viewModel.onDealNavigated()
  }
}
