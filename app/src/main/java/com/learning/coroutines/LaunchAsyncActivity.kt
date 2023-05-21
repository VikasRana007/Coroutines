package com.learning.coroutines

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LaunchAsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_async)

//        GlobalScope.launch {
//            try{
//            doSomethingAndThrowException()
//            } catch (e: Exception) {
//                // handle exception
//            }
//        }


//        GlobalScope.async {
//            doSomethingAndThrowException()
//        }
    }



    private fun doSomethingAndThrowException() {
        Toast.makeText(this, "Exception Coming . . . ", Toast.LENGTH_SHORT).show()
        throw Exception("Some Exception")
    }
}