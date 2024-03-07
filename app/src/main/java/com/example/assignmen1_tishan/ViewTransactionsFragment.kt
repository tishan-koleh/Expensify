package com.example.assignmen1_tishan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmen1_tishan.dataBase.Expense
import com.example.assignmen1_tishan.dataBase.ExpenseDatabase
import com.example.assignmen1_tishan.dataBase.ExpenseRepositary
import com.example.assignmen1_tishan.databinding.FragmentViewTransactionsBinding
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModel
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewTransactionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewTransactionsFragment : Fragment() {
    private lateinit var binding : FragmentViewTransactionsBinding
    private lateinit var viewmodelRecycler : ExpenseDatabaseViewModel
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_transactions, container, false)

        val dao = ExpenseDatabase.getInstance(requireContext()).expenseDao
        val repositary = ExpenseRepositary(dao)
        val factory = ExpenseDatabaseViewModelFactory(repositary)
        viewmodelRecycler = ViewModelProvider(this,factory).get(ExpenseDatabaseViewModel::class.java)

        binding.lifecycleOwner = this
        viewmodelRecycler = ViewModelProvider(this,factory).get(ExpenseDatabaseViewModel::class.java)

            binding.myRecyclerView.layoutManager = LinearLayoutManager(this@ViewTransactionsFragment.requireContext())
            viewmodelRecycler.expenses.observe(viewLifecycleOwner, Observer {
                Log.i("MYTAG",it.toString())
                binding.myRecyclerView.adapter = MyRecyclerViewAdapter(it,{selectedItem:Expense->recyclerViewClicked(selectedItem)})
            })








        return binding.root
    }
    fun recyclerViewClicked(expense:Expense){
        Toast.makeText(this@ViewTransactionsFragment.requireContext(),"${expense.name} is selected",Toast.LENGTH_SHORT).show()

    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewTransactionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewTransactionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}