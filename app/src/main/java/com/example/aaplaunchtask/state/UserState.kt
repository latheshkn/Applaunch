package com.example.aaplaunchtask.state

import com.example.aaplaunchtask.adapter.UserListAdapter
import com.example.aaplaunchtask.model.WeatherMap
import com.example.aaplaunchtask.room.UserList

sealed class UserState{
    object Init : UserState()
    data class GetUser(val adapter:UserListAdapter,val user:ArrayList<UserList>) : UserState()
    data class NavigateToWeather(val position:Int) : UserState()
}
