package com.learning.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        CoroutineScope(Dispatchers.IO).launch {
//            getLaunchFollowers()

            getAsyncFollowers()
        }

    }


    /**
     * This is the example of launch coroutine builder
     */

    private suspend fun getLaunchFollowers(){
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch{
            fbFollowers = getLaunchFbFollowers()
        }
        job.join()                                 // This Line is use to help to take pause of execution flow yet result get out.
        println("FB Followers : $fbFollowers")
//        Log.d("TAG", "$fbFollowers")
    }


    private suspend fun getLaunchFbFollowers() : Int{
        delay(1500)    // This is the suspending modifier as like yield()
        return 34
    }


    /**
     * This is the example of async coroutine builder
     */
    private suspend fun getAsyncFollowers(){
        val job = CoroutineScope(Dispatchers.IO).async{
            // Last Statement will be the return type of async coroutine builder
            getAsyncFbFollowers()
        }
        job.await()                                // This Line is use to help to take pause of execution flow yet result get out.
        println("FB Followers : ${job.await()}")
//        Log.d("TAG", "$fbFollowers")
    }

    private suspend fun getAsyncFbFollowers() : Int{
        delay(1500)    // This is the suspending modifier as like yield()
        return 34
    }
}