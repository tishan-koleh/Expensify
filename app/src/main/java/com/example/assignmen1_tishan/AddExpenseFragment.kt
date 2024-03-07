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
import com.example.assignmen1_tishan.dataBase.ExpenseDatabase
import com.example.assignmen1_tishan.dataBase.ExpenseRepositary
import com.example.assignmen1_tishan.databinding.FragmentAddExpenseBinding
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModel
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModelFactory
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddExpenseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddExpenseFragment : Fragment() {
    private lateinit var binding:FragmentAddExpenseBinding
    private lateinit var viewModel : ExpenseDatabaseViewModel
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_expense, container, false)
        val dao = ExpenseDatabase.getInstance(requireContext()).expenseDao
        val repositary = ExpenseRepositary(dao)
        val factory = ExpenseDatabaseViewModelFactory(repositary)
        viewModel = ViewModelProvider(this,factory).get(ExpenseDatabaseViewModel::class.java)
        binding.myViewModel=viewModel
        binding.lifecycleOwner = this
        binding.addButton.setOnClickListener {
            try {
                viewModel.addButtonAction()
                viewModel.expenses.observe(viewLifecycleOwner, Observer {
                    Log.i("Tishan","${it.toString()}")
                    //Toast.makeText(this@AddExpenseFragment.requireContext(),it.toString(),Toast.LENGTH_LONG).show()
                })
            }
            catch (e:Exception)
            {
                Log.i("MYTAG","${e.toString()}")
                Toast.makeText(this@AddExpenseFragment.requireContext(),e.toString(),Toast.LENGTH_LONG).show()
            }


        }



        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddExpenseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddExpenseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}