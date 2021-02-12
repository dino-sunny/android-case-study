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
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem

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
    pullRefresh()
    return binding.root
  }

  //Calling api in 'onStart' to load the list before user interacting with UI
  override fun onStart() {
    super.onStart()
    getDeals()
  }

  private fun pullRefresh() {
    binding.swiperefresh.setOnRefreshListener {
      getDeals()
    }
  }

  //Get the posts if network connection available else .
  //network error message
  private fun getDeals() {
    if (NetworkCheck.isOnline(requireContext())) {
      viewModel.getDeals()
      binding.loader.visibility = View.VISIBLE
    } else{
      binding.noInternet.visibility = View.VISIBLE
      binding.retryButton.visibility = View.VISIBLE
    }
  }

  //Observing live data
  private fun setObservers() {
    viewModel.eventRetry.observe(viewLifecycleOwner, { isClicked ->
      if (isClicked) {
        getDeals()
        viewModel.onRetryClicked()
      }
    })
    //Navigate action handling
    viewModel.eventNavigateDeal.observe(viewLifecycleOwner) { post ->
      post?.let { navigateToDetails(post)}
    }
    viewModel.responsePosts.observe(viewLifecycleOwner) {Products->
      Products?.let { list ->
        binding.swiperefresh.isRefreshing = false
        binding.loader.visibility = View.INVISIBLE
        binding.noInternet.visibility = View.INVISIBLE
        binding.retryButton.visibility = View.INVISIBLE
        if (!list.products.isNullOrEmpty()) {
          dealsAdapter.submitList(Products.products)
        }
      }
    }
  }

  //Set Adapter and Item Click listener
  private fun setListAdapter() {
    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    dealsAdapter = DealItemAdapter(DealItemListener {deal: DealItem ->
      viewModel.onDealClicked(deal)
    })
    binding.recyclerView.adapter = dealsAdapter
  }

  //Navigate to Details with deal info
  private fun navigateToDetails(deal: DealItem) {
    val mData = Gson().toJson(deal)
    findNavController().navigate(
            DealListFragmentDirections
                    .actionDealListFragmentToDealItemFragment(mData)
    )
    viewModel.onDealNavigated()
  }
}
