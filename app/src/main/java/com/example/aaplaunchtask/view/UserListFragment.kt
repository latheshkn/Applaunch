package com.example.aaplaunchtask.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.SharePref
import com.example.aaplaunchtask.adapter.UserListAdapter
import com.example.aaplaunchtask.databinding.FragmentUserListBinding
import com.example.aaplaunchtask.room.UserList
import com.example.aaplaunchtask.room.UserListDatabase
import com.example.aaplaunchtask.state.UserState
import com.example.aaplaunchtask.util.SwipeGesture
import com.example.aaplaunchtask.util.navigation
import com.example.aaplaunchtask.viewmodel.UserViewModel



class UserListFragment : Fragment() {
    private lateinit var database: UserListDatabase
    val viewModel:UserViewModel by viewModels()
    private lateinit var binding:FragmentUserListBinding
    private lateinit var adapter:UserListAdapter
    lateinit var list: ArrayList<UserList>
    lateinit var pref: SharePref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentUserListBinding.inflate(inflater,container,false)
        database =
            Room.databaseBuilder(requireContext(), UserListDatabase::class.java, "ContactDb")
                .build()
        viewModel.getUser(database)

        pref = SharePref(requireContext())

        if (!pref.getUser("Login")){
            navigation(requireActivity().supportFragmentManager).navigate(R.id.loginFragment)
        }

        binding.logoutBtn.setOnClickListener{
            pref.logout()
            navigation(requireActivity().supportFragmentManager).navigate(R.id.loginFragment)
        }

        viewModel.stateObserver.observe(this,{
            when(it){
                is UserState.GetUser->{

                    binding.userRecyclerView.adapter=it.adapter
                    list=it.user
                }
                is UserState.NavigateToWeather->{
                    navigation(requireActivity().supportFragmentManager).navigate(R.id.weatherFragment)
                }
                else->{

                }
            }
        })
        val swipeGesture=object : SwipeGesture(requireContext()) {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               when(direction){
                   ItemTouchHelper.RIGHT->{
                       viewModel.deleteUser(database, list[viewHolder.adapterPosition].id,viewHolder.adapterPosition)
                   }
               }
            }
        }
        val touchHelper=ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.userRecyclerView)
        binding.ivAdd.setOnClickListener {
            navigation(requireActivity().supportFragmentManager).navigate(R.id.addUserFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflaterMenu: MenuInflater = inflater
        inflaterMenu.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                navigation(requireActivity().supportFragmentManager).navigate(R.id.addUserFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}