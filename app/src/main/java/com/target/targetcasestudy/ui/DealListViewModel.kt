package com.target.targetcasestudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.apiHandler.ApiService
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import kotlinx.coroutines.launch

class DealListViewModel : ViewModel(){

    private val _navigateToDeal = MutableLiveData<DealItem>()
    val eventNavigateDeal: LiveData<DealItem> get() = _navigateToDeal

    private var postResponse =  MutableLiveData<Products>()
    val responsePosts: LiveData<Products> get() = postResponse

    fun onDealClicked(deal:DealItem){
        _navigateToDeal.value = deal
    }
    fun onDealNavigated() {
        _navigateToDeal.value = null
    }

    //Get Posts from API
    fun getDeals() {
        viewModelScope.launch {
            val response =  ApiService.instance.getDeals()
            try {
                if (response.isSuccessful){
                    postResponse.value = response.body()
                }
            } catch (e: Exception){
                postResponse.value = null
            }
        }
    }
}