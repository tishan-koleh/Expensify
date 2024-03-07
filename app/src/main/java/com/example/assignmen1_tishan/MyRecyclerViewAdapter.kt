package com.example.assignmen1_tishan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assignmen1_tishan.dataBase.Expense
import com.example.assignmen1_tishan.databinding.FragmentMinimumBinding
import com.example.assignmen1_tishan.databinding.MyListItemBinding
import org.w3c.dom.Text

class MyRecyclerViewAdapter(val Expenselist : List<Expense>,private val clickListner:(Expense)->Unit):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : MyListItemBinding  = DataBindingUtil.inflate(inflater,R.layout.my_list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return Expenselist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(Expenselist[position],clickListner)
    }

}

class MyViewHolder(val binding: MyListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(expense:Expense,clickListner:(Expense)->Unit){
        binding.firstNameTextView.text = expense.name
        binding.priceTextView.text = expense.price.toString()
        binding.dateTextView.text = expense.date
        binding.listItemLayout.setOnClickListener{
            clickListner(expense)
            var bundle = bundleOf("item_id" to expense.id.toString(),
                "item_name" to expense.name,
                "item_date" to expense.date,
                "item_price" to expense.price.toString())
            it.findNavController().navigate(R.id.action_viewTransactionsFragment_to_editDeleteFragment,bundle)
        }

    }


}