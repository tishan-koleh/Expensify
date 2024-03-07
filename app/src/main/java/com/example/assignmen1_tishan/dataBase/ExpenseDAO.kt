package com.example.assignmen1_tishan.dataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseDAO {

    @Insert
    fun inserExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("DELETE FROM expense_table")
    fun deleteALl()

    @Query("Select * from expense_table")
    fun getExpense():LiveData<List<Expense>>

    @Query("Select SUM(price) from expense_table")
    fun getTotal():Double

    @Query("Select AVG(price) from expense_table")
    fun getAvg():Double

    @Query("select MAX(price) from expense_table")
    fun getMax():Double

    @Query("select MIN(price) from expense_table")
    fun getMin():Double


//    @Query("SELECT * FROM expense_table WHERE price = (SELECT MAX(price) FROM expense_table)")
//    fun getMaxExpense():LiveData<Expense>
//
//    @Query("SELECT * FROM expense_table WHERE price = (SELECT MIN(price) FROM expense_table)")
//    fun getMinExpense():LiveData<Expense>

    @Query("SELECT * FROM expense_table WHERE price = (SELECT MAX(price) FROM expense_table)")
    fun getMaxExpense():Expense

    @Query("SELECT * FROM expense_table WHERE price = (SELECT MIN(price) FROM expense_table)")
    fun getMinExpense():Expense

}