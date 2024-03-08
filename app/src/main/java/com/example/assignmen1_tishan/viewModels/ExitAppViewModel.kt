package com.example.assignmen1_tishan.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExitAppViewModel:ViewModel() {
    val exit = MutableLiveData<Boolean>(false)

    suspend fun getExitValue():Boolean{
        //var x :Boolean? = null
        var deff = viewModelScope.async {
            exit.value
        }.await()
        return  deff!!
    }
}