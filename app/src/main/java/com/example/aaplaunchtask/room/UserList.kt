package com.example.aaplaunchtask.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="user")
data class UserList(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val firstName:String,
    val LastName:String,
    val email:String
)
