package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.utilities.ImageHandler.setGlideImage


class DealItemFragment : Fragment() {
  private lateinit var binding: FragmentDealItemBinding
  private lateinit var deal: DealItem

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    // Inflate the layout for this fragment
    binding = FragmentDealItemBinding.inflate(inflater,container,false)
    return  binding.root
  }
  override fun onStart() {
    super.onStart()
    getDataFromIntent()
  }

  //Get post details from arguments passed.
  private fun getDataFromIntent() {
    if (arguments?.get("Deal")!=null) {
      val jsonString = arguments?.get("Deal") as String?
      deal = Gson().fromJson(jsonString, DealItem::class.java)
      setData()
    }
  }

  //Set data to the UI using binding
  private fun setData() {
    binding.deal = deal
    setGlideImage(deal.image_url, binding.dealItemImage)
  }
}
