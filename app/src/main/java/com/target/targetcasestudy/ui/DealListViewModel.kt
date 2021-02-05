package com.target.targetcasestudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.DealItem

class DealListViewModel : ViewModel(){
    private val _navigateToDeal = MutableLiveData<DealItem>()
    val eventNavigateDeal: LiveData<DealItem> get() = _navigateToDeal

    fun onDealClicked(deal:DealItem){
        _navigateToDeal.value = deal
    }
    fun onDealNavigated() {
        _navigateToDeal.value = null
    }

}