package com.example.assignmen1_tishan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assignmen1_tishan.dataBase.Expense
import com.example.assignmen1_tishan.databinding.FragmentMinimumBinding
import com.example.assignmen1_tishan.databinding.MyListItemBinding
import org.w3c.dom.Text

class MyRecyclerViewAdapter(val Expenselist : List<Expense>):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : MyListItemBinding  = DataBindingUtil.inflate(inflater,R.layout.my_list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return Expenselist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(Expenselist[position])
    }

}

class MyViewHolder(val binding: MyListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(expense:Expense){
        binding.firstNameTextView.text = expense.name
        binding.priceTextView.text = expense.price.toString()
        binding.dateTextView.text = expense.date

    }


}