package com.example.assignmen1_tishan.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class ExpenseDatabase:RoomDatabase(){

    abstract val expenseDao : ExpenseDAO

    companion object{
        @Volatile
        private var Instance : ExpenseDatabase? = null

        fun getInstance(context: Context):ExpenseDatabase{
            synchronized(this){
                if(Instance==null){
                    Instance = Room.databaseBuilder(context,ExpenseDatabase::class.java,"expenseDB").build()
                }
            }
            return Instance!!
        }
    }
}