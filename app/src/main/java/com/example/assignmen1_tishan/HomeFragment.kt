package com.example.assignmen1_tishan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.assignmen1_tishan.dataBase.ExpenseDatabase
import com.example.assignmen1_tishan.dataBase.ExpenseRepositary
import com.example.assignmen1_tishan.databinding.FragmentHomeBinding
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModel
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.math.truncate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModelHome : ExpenseDatabaseViewModel
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.addTransaction.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }
        binding.textView.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }
        val dao = ExpenseDatabase.getInstance(requireContext()).expenseDao
        val repositary = ExpenseRepositary(dao)
        val factory = ExpenseDatabaseViewModelFactory(repositary)
        viewModelHome = ViewModelProvider(this,factory).get(ExpenseDatabaseViewModel::class.java)
        binding.myViewModel=viewModelHome
        binding.lifecycleOwner = this



        try {

            lifecycleScope.launch {
                //Average
                var average = viewModelHome.getAVG()
                var truncatedNumber: Double = truncate(average * 100) / 100
                binding.averageTv.text = "Rs. "+truncatedNumber.toString()
//                val bundleAvg = bundleOf("average_transaction" to average.toString())
//                binding.avgTransaction.setOnClickListener {
//                    it.findNavController().navigate(R.id.action_homeFragment_to_avgFragment, bundleAvg)
//                }
                //Average


                //Maximum
                var maxExpense = viewModelHome.getMaxExpense().name
                var dateOfMaxExpense = viewModelHome.getMaxExpense().date
                var maxi = viewModelHome.getMaxi()
                var bundleMaxi = bundleOf("maxi_transaction" to maxi.toString(),
                    "max_expense_name" to maxExpense,"date_of_max_expense" to dateOfMaxExpense)
                binding.maxTransaction.setOnClickListener{
                    //Toast.makeText(this@HomeFragment.requireContext(),maxExpense,Toast.LENGTH_LONG).show()
                    it.findNavController().navigate(R.id.action_homeFragment_to_maximumFragment,bundleMaxi)
                }
                //Maximum


                //Minimum
                var minExpense = viewModelHome.getMinExpense().name
                var minExpenseDate = viewModelHome.getMinExpense().date
                var mini = viewModelHome.getMini()
                var bundleMini = bundleOf("mini_transaction" to mini.toString(),
                    "min_expense_name" to minExpense,"date_of_min_expense" to minExpenseDate)
                binding.minTransaction.setOnClickListener{
                    //Toast.makeText(this@HomeFragment.requireContext(),minExpense,Toast.LENGTH_LONG).show()
                    it.findNavController().navigate(R.id.action_homeFragment_to_minimumFragment,bundleMini)
                }
                //Minimum

                //Total
                var total = viewModelHome.getTotal()
                var truncatedTotal: Double = truncate(total * 100) / 100
                binding.totalTv.text = "Rs. "+ truncatedTotal.toString()
//                var bundleTotal = bundleOf("total_transaction" to total.toString())
//                binding.totalTransaction.setOnClickListener {
//                    it.findNavController().navigate(R.id.action_homeFragment_to_totalFragment,bundleTotal)
//                }

                //Total



            }
        }
        catch (e:Exception)
        {

            Toast.makeText(this@HomeFragment.requireContext(),e.toString(),Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}