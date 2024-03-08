package com.example.assignmen1_tishan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.assignmen1_tishan.viewModels.ExitAppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var exitAppViewModel: ExitAppViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onBackPressed() {
        // Check if the current destination is the home fragment
        var exit = false
        lifecycleScope.launch(Dispatchers.IO) {
            exit = exitAppViewModel.getExitValue()
        }
        if(exit == true){
            finish()
        }
        if (navController.currentDestination?.id == R.id.homeFragment) {
            // If on the home fragment, finish the activity to exit the app
            AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes") { dialog, which ->
                    // If the user confirms, finish the activity to exit the app
                    finish()
                }
                .setNegativeButton("No", null) // Do nothing if the user cancels
                .show()
        } else {
            // If not on the home fragment, let the system handle back button behavior
            super.onBackPressed()
        }
    }




}