package com.example.aaplaunchtask.room


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.aaplaunchtask.R
import com.example.aaplaunchtask.webservice.ModelRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
private const val TAG = "checkDatabase"

class RoomActivity : AppCompatActivity() {
 private lateinit var database: UserListDatabase
 private lateinit var modelRepository: ModelRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database=Room.databaseBuilder(applicationContext,UserListDatabase::class.java,"ContactDb").build()
        modelRepository= ModelRepository(database)
        GlobalScope.launch {

           modelRepository.addUser(UserList(0,"lathesh","k n","lathesh@gmail.com"))

        }
//        getDataOnClick()

    }

    private fun getDataOnClick(){

//         database.userDao().getUser().observe(this,{
//             Log.d(TAG,it.toString())
//         })

        Toast.makeText(applicationContext,"clicked",Toast.LENGTH_SHORT).show()
    }

}