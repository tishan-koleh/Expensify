package com.example.assignmen1_tishan.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String,
    @ColumnInfo(name = "price")
    val price:Double,
    val date : String
)
