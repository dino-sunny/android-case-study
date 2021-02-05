package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.target.targetcasestudy.data.DealItem

import com.target.targetcasestudy.databinding.FragmentDealListBinding


class DealListFragment : Fragment() {
  private lateinit var binding : FragmentDealListBinding
  private lateinit var viewModel: DealListViewModel

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

  private fun setObservers() {
    //Navigate action handling
    viewModel.eventNavigateDeal.observe(viewLifecycleOwner) { post ->
      post?.let { navigateToDetails(post)}
    }
  }

  private fun setListAdapter() {
    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.recyclerView.adapter = DealItemAdapter(DealItemListener {deal: DealItem ->
      viewModel.onDealClicked(deal)
    })
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
