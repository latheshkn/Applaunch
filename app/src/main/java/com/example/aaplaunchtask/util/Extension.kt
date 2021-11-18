package com.example.aaplaunchtask.util

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.aaplaunchtask.R

fun navigation(fragmentManager: FragmentManager): NavController {
    lateinit var navHostController: NavController
    val navHostFragment: NavHostFragment =
        fragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    navHostController = navHostFragment.navController
    return navHostController
}

fun emptyCheck(value: String): Boolean {
    return value.isEmpty()
}

fun showToast(context: Context, message: String, visible: Boolean) {
    if (visible) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }

}