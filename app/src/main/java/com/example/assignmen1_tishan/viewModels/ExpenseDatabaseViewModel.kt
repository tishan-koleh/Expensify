package com.example.assignmen1_tishan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmen1_tishan.dataBase.Expense
import com.example.assignmen1_tishan.dataBase.ExpenseRepositary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpenseDatabaseViewModel(private val repo : ExpenseRepositary):ViewModel() {

    val expenses = repo.expense
//    val maxExpense = repo.maxExpense
//    val minExpense = repo.minExpense

    val inputItemName = MutableLiveData<String>()
    val inputPrice = MutableLiveData<String>()
    val inputDate = MutableLiveData<String>()


    fun addButtonAction()
    {
        val name = inputItemName.value?:return
        val price = inputPrice.value?.toDoubleOrNull()?:return
        val date = inputDate.value?:return
        insert(Expense(0,name,price,date))
        inputItemName.value = ""
        inputPrice.value = ""
        inputDate.value = ""
    }
    fun insert(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(expense)
    }

    fun update(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(expense)
    }

    fun delete(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(expense)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }

    suspend fun getAVG():Double{
        var x = 0.0
        viewModelScope.launch(Dispatchers.IO) {
            val deferredValue = async { repo.getAvg() }
            x = deferredValue.await()
        }.join() // Wait for the coroutine to finish before returning
        return x
    }

    suspend fun getMaxi():Double{
        var x = 0.0
        viewModelScope.launch(Dispatchers.IO) {
            val deffered =  async { repo.getMaxi() }
            x = deffered.await()
        }.join()
        return x
    }

    suspend fun getMini():Double{
        var x = 0.0
        viewModelScope.launch(Dispatchers.IO) {
            var defferd =  async { repo.getMin() }
            x = defferd.await()
        }.join()
        return x
    }


    suspend fun getTotal():Double{
        var x = 0.0
        viewModelScope.launch(Dispatchers.IO) {
            var defferd = async { repo.getTotal() }
            x = defferd.await()
        }.join()
        return x
    }

    suspend fun getMaxExpense():Expense{
        var x : Expense? = null
        viewModelScope.launch(Dispatchers.IO) {
            var deff = async { repo.getMaxExpense() }
            x = deff.await()
        }.join()
        return x!!
    }


    suspend fun getMinExpense():Expense{
        var x :Expense? = null
        viewModelScope.launch(Dispatchers.IO){
            var deff = async { repo.getMinExpense() }
            x = deff.await()
        }.join()
        return x!!
    }


    var id = MutableLiveData<Int>()
    var name = MutableLiveData<String>()
    var price = MutableLiveData<Double>()
    var date = MutableLiveData<String>()
    suspend fun setExpenseData(expense: Expense){
        viewModelScope.launch(Dispatchers.IO){
            var d1 = async { expense.id }
            var d2 = async { expense.name }
            var d3 = async { expense.price }
            var  d4 = async { expense.date }

            withContext(Dispatchers.Main){
                id.value = d1.await()
                name.value = d2.await()
                price.value = d3.await()
                date.value = d4.await()
            }


        }.join()
    }



    suspend fun getCount():Int{
        var x :Int? = null
        viewModelScope.launch(Dispatchers.IO){
            var deff = async { repo.getCount() }
            x = deff.await()
        }.join()
        return x!!
    }



}