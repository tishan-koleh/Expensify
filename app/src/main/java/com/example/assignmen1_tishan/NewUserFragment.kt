package com.example.assignmen1_tishan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.assignmen1_tishan.authentication.SharedPreferencesManager
import com.example.assignmen1_tishan.databinding.FragmentNewUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewUserFragment : Fragment() {
    private lateinit var binding : FragmentNewUserBinding
    private lateinit var preferences:SharedPreferencesManager
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_new_user, container, false)

        GlobalScope.launch(Dispatchers.IO) {
             preferences = SharedPreferencesManager.getInstance(this@NewUserFragment.requireContext(),"123")
        }

        binding.createUser.setOnClickListener {

            if(binding.nameTv.text.toString() == "" || binding.passwordTv.text.toString()== ""){

                AlertDialog.Builder(this.requireContext())
                    .setMessage("Please Enter Some values")
                    .setPositiveButton("Yes") { dialog, which ->
                        // If the user confirms, go to the home page

                    }
                    .setNegativeButton("No") { dialog, which ->
                        // go back to ask login page
                        it.findNavController().navigate(R.id.action_newUserFragment_to_loginAskFragment)
                    }
                    .show()
            }
            else{
                val name = binding.nameTv.text.toString()
                val pass = binding.passwordTv.text.toString()
                preferences.saveData(name,pass)
                var bundle = bundleOf("user_name" to name)

                AlertDialog.Builder(this.requireContext())
                    .setMessage("Are you sure you want to create an account?")
                    .setPositiveButton("Yes") { dialog, which ->
                        // If the user confirms, go to the home page
                        it.findNavController().navigate(R.id.action_newUserFragment_to_homeFragment,bundle)
                    }
                    .setNegativeButton("No") { dialog, which ->
                        // go back to ask login page
                        it.findNavController().navigate(R.id.action_loginFragment_to_loginAskFragment)
                    }
                    .show()
            }





//            Toast.makeText(this@NewUserFragment.requireContext(),"User Created",Toast.LENGTH_SHORT).show()
//            it.findNavController().navigate(R.id.action_newUserFragment_to_homeFragment,bundle)
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
         * @return A new instance of fragment NewUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}