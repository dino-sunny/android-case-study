package com.target.targetcasestudy.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentDialogViewModel : ViewModel(){

    private val _eventSubmit = MutableLiveData<Boolean>()
    val eventSubmit: LiveData<Boolean>
        get() = _eventSubmit

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean>
        get() = _eventCancel

    fun onSubmitClick(){
        _eventSubmit.value = true
    }
    fun onSubmitClickComplete(){
        _eventSubmit.value = false
    }
    fun onCancelClick(){
        _eventCancel.value = true
    }
    fun onCancelClickComplete(){
        _eventCancel.value = false
    }
}