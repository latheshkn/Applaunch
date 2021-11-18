package com.example.aaplaunchtask.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.databinding.ActivityMainBinding
import com.example.aaplaunchtask.util.navigation

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.toolbar

        toolbar.inflateMenu(R.menu.add_menu)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                navigation(this.supportFragmentManager).navigate(R.id.addUserFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            System.exit(0)
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(
            this, "Please click BACK again to exit",
            Toast.LENGTH_SHORT
        ).show()
    }
}