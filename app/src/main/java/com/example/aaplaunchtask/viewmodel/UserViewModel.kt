package com.example.aaplaunchtask.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaplaunchtask.SingleLiveEvent
import com.example.aaplaunchtask.adapter.UserListAdapter
import com.example.aaplaunchtask.room.UserList
import com.example.aaplaunchtask.room.UserListDatabase
import com.example.aaplaunchtask.state.UserState
import com.example.aaplaunchtask.webservice.ModelRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private lateinit var adapter:UserListAdapter
    private var state: UserState = UserState.Init
        set(value) {
            field = value
            publishState(state)
        }
    val stateObserver: SingleLiveEvent<UserState> by lazy(::SingleLiveEvent)

    private fun publishState(state: UserState) {
        stateObserver.setValue(state)
    }

    fun addUser(userDb: UserListDatabase, firstName: String, lastName: String, email: String) {

        viewModelScope.launch {
            ModelRepository(db = userDb).addUser(UserList(0, firstName, lastName, email))

        }
    }

    fun getUser(database: UserListDatabase) {
        viewModelScope.launch {

            val user = ModelRepository(database).showUser()
            adapter= UserListAdapter(user,this@UserViewModel)
            state = UserState.GetUser(adapter,user)
        }
    }

    fun deleteUser(database: UserListDatabase,id:Long,position: Int){
        viewModelScope.launch {
            ModelRepository(database).deleteUser(id)
           adapter.delete(position)
        }
    }
    fun navigateToWeather(position: Int){
        state = UserState.NavigateToWeather(position)
    }
}