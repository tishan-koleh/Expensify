package com.example.assignmen1_tishan.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmen1_tishan.dataBase.ExpenseRepositary

class ExpenseDatabaseViewModelFactory(private val repo:ExpenseRepositary):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExpenseDatabaseViewModel::class.java)) {
            return ExpenseDatabaseViewModel(repo) as T
        }
        throw IllegalArgumentException("Error")
    }
}