package com.example.aaplaunchtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview. widget.RecyclerView
import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.databinding.IncludeUserListBinding
import com.example.aaplaunchtask.room.UserList
import com.example.aaplaunchtask.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.include_user_list.view.*

class UserListAdapter(private var list: ArrayList<UserList>,val viewModel: UserViewModel):
    RecyclerView.Adapter<UserListAdapter.Vholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vholder {
       val binding=IncludeUserListBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.include_user_list, parent, false))
        return Vholder(binding.root)
    }

    override fun onBindViewHolder(holder: Vholder, position: Int) {
        holder.tvName.text= list[position].firstName+" "+list[position].LastName
        holder.tvEmail.text= list[position].email

        holder.itemView.setOnClickListener {
            viewModel.navigateToWeather(position)
        }
    }
    fun delete(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Vholder(itemView:View):RecyclerView.ViewHolder(itemView) {
      var tvName:TextView=itemView.tvName
      var tvEmail:TextView=itemView.tvFirstName


    }
}