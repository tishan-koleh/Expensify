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
import androidx.navigation.findNavController
import com.example.assignmen1_tishan.authentication.SharedPreferencesManager
import com.example.assignmen1_tishan.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var preference:SharedPreferencesManager
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        preference = SharedPreferencesManager.getInstance(this@LoginFragment.requireContext(),"123")
        binding.button.setOnClickListener {
            val name = binding.userIdTv.text.toString()
            val pass = binding.userPasswordTv.text.toString()
            var bundle = bundleOf("user_name" to name)

            if(pass.equals(preference.getData(name,""))) {
                it.findNavController().navigate(R.id.action_loginFragment_to_homeFragment,bundle)
            }
            else{
                Toast.makeText(this@LoginFragment.requireContext(),"User Not Found",Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_loginFragment_to_newUserFragment)
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}