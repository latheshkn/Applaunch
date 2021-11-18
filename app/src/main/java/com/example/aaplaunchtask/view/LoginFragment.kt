package com.example.aaplaunchtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.SharePref
import com.example.aaplaunchtask.databinding.FragmentLoginBinding
import com.example.aaplaunchtask.util.emptyCheck
import com.example.aaplaunchtask.util.navigation
import com.example.aaplaunchtask.util.showToast


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var pref: SharePref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        pref = SharePref(requireContext())


        if (pref.getUser("Login")){
            navigation(requireActivity().supportFragmentManager).navigate(R.id.userListFragment)
        }else{
            binding.btnLogin.setOnClickListener {

                when {
                    emptyCheck(binding.etFirstName.text.toString()) -> {
                        showToast(requireContext(), getString(R.string.user_name), true)
                    }

                    emptyCheck(binding.etLastName.text.toString()) -> {
                        showToast(requireContext(), getString(R.string.enter_password), true)
                    }

                    else -> {
                        if (binding.etFirstName.text.toString() == getString(R.string.user_email) && binding.etLastName.text.toString() == getString(
                                R.string.user_password
                            )
                        ) {
                            navigation(requireActivity().supportFragmentManager).navigate(R.id.userListFragment)
                            pref.putBoolean("Login",true)
                        } else {
                            showToast(requireContext(), getString(R.string.not_match), true)
                        }

                    }
                }
            }
        }


        return binding.root
    }


}