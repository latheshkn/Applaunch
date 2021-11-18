package com.example.aaplaunchtask.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.databinding.FragmentAddUserBinding
import com.example.aaplaunchtask.room.UserListDatabase
import com.example.aaplaunchtask.util.emptyCheck
import com.example.aaplaunchtask.util.navigation
import com.example.aaplaunchtask.util.showToast
import com.example.aaplaunchtask.viewmodel.UserViewModel


class AddUserFragment : Fragment() {
    private val TAG = "AddUserFragment"
    private lateinit var database: UserListDatabase
    lateinit var binding: FragmentAddUserBinding
    private val viewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database =
            Room.databaseBuilder(requireContext(), UserListDatabase::class.java, "ContactDb")
                .build()


        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        binding.btnSave.setOnClickListener {
            saveUser()
        }

        binding.backBtn.setOnClickListener {
            navigation(requireActivity().supportFragmentManager).navigate(R.id.userListFragment)
        }

        return binding.root
    }

   private fun saveUser() {

        when {
            emptyCheck(binding.etFirstName.text.toString()) -> {
                showToast(requireContext(), getString(R.string.enter_first_name), true)
            }

            emptyCheck(binding.etLastName.text.toString()) -> {
                showToast(requireContext(), getString(R.string.enter_last_name), true)
            }
            emptyCheck(binding.etEmail.text.toString()) -> {
                showToast(requireContext(), getString(R.string.enter_your_email), true)
            }
            else->{
            viewModel.addUser(database, binding.etFirstName.text.toString(), binding.etLastName.text.toString(), binding.etEmail.text.toString())
                navigation(requireActivity().supportFragmentManager).navigate(R.id.userListFragment)

            }
        }

    }
    }