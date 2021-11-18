package com.example.aaplaunchtask.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserListDao {

    @Insert
    suspend fun insertUser(contact: UserList)

   @Query("DELETE FROM user WHERE id = :userId")
   suspend fun deleteUser(userId: Long)

    @Update
   suspend fun updateContact(contact: UserList)

    @Query("select * from user ORDER BY id DESC")
   suspend  fun getUser():List<UserList>

}