package com.example.aaplaunchtask.webservice

import androidx.lifecycle.LiveData
import com.example.aaplaunchtask.room.UserList
import com.example.aaplaunchtask.room.UserListDatabase

class ModelRepository(private val db:UserListDatabase?=null,val api: Api?=null)  {
    suspend fun getWeather(lat: Double,lon:Double,units:String,appid:String) =api?.getWeatherMap(lat,lon,units,appid)

    suspend fun addUser(userList: UserList){
        db?.userDao()?.insertUser(userList)
    }

   suspend fun showUser(): ArrayList<UserList> {
      return  db?.userDao()?.getUser()!! as ArrayList<UserList>
    }
    suspend fun deleteUser(id:Long) {
         db?.userDao()?.deleteUser(id)
    }

}