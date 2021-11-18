package com.example.aaplaunchtask.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserList::class],version = 1)
abstract class UserListDatabase :RoomDatabase(){

    abstract fun userDao(): UserListDao
}