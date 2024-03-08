package com.example.assignmen1_tishan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.assignmen1_tishan.dataBase.Expense
import com.example.assignmen1_tishan.dataBase.ExpenseDatabase
import com.example.assignmen1_tishan.dataBase.ExpenseRepositary
import com.example.assignmen1_tishan.databinding.FragmentEditDeleteBinding
import com.example.assignmen1_tishan.databinding.MyListItemBinding
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModel
import com.example.assignmen1_tishan.viewModels.ExpenseDatabaseViewModelFactory
import kotlinx.coroutines.launch
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditDeleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditDeleteFragment : Fragment() {
    private lateinit var binding: FragmentEditDeleteBinding
    private lateinit var viewModelEdit : ExpenseDatabaseViewModel
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_delete, container, false)

        var id = requireArguments().getString("item_id")?.toInt()
        var name = requireArguments().getString("item_name")
        var date = requireArguments().getString("item_date")
        var price = requireArguments().getString("item_price")
        val dao = ExpenseDatabase.getInstance(this@EditDeleteFragment.requireContext()).expenseDao
        val repo = ExpenseRepositary(dao)
        val factory = ExpenseDatabaseViewModelFactory(repo)
        viewModelEdit = ViewModelProvider(this,factory).get(ExpenseDatabaseViewModel::class.java)
        binding.myViewModel = viewModelEdit
        binding.lifecycleOwner = this

//        binding.homeButton.setOnClickListener {
//            it.findNavController().navigate(R.id.action_editDeleteFragment_to_homeFragment)
//        }
        binding.deleteButton.setOnClickListener {
            viewModelEdit.delete(Expense(id!!,name!!,price!!.toDouble(),date!!))
            Toast.makeText(this@EditDeleteFragment.requireContext(),"Details Deleted!",Toast.LENGTH_SHORT).show()
            it.findNavController().navigate(R.id.action_editDeleteFragment_to_viewTransactionsFragment)

        }
        lifecycleScope.launch() {
            viewModelEdit.setExpenseData(Expense(id!!, name!!, price!!.toDouble(), date!!))
        }



            binding.updateSaveButton.setOnClickListener {
                name = binding.nameTv.text.toString()
                date = binding.dateTv.text.toString()
                price = binding.priceTv.text.toString()
                try {

                    viewModelEdit.update(Expense(id!!, name!!, price!!.toDouble(), date!!))
                    Toast.makeText(this@EditDeleteFragment.requireContext(),"Details Updated!",Toast.LENGTH_SHORT).show()

                    it.findNavController().navigate(R.id.action_editDeleteFragment_to_viewTransactionsFragment)

                }catch (e:Exception)
                {
                    Log.d("MYTAG",e.toString())
                    Toast.makeText(this@EditDeleteFragment.requireContext(),e.toString(),Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment EditDeleteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditDeleteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}