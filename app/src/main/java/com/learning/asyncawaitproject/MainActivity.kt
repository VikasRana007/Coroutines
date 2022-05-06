package com.learning.asyncawaitproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    lateinit var tvUserMessage : TextView
    lateinit var btnDownloadUserData : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvUserMessage = findViewById(R.id.tvUserMessage)
        btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
        // for dispatcher parallel decomposition using with async and await to get data from different
        // sources and combined the result
        CoroutineScope(Main).launch {
            Log.i("MyTag", "Calculation Started")
            // This is not a parallel decomposition it is sequential.  first function execute and wait for 10 second &
            // return it s stock value , after return value function 2 will run wait for 8 sec and after that return its stock value
          //  val stock1 = async(IO) { getStockCount1() }    // in async we need to invoke await function of both async builder
          //  val stock2 = async(IO) { getStockCount2() }
          //  val total = stock1.await() + stock2.await()
//            Toast.makeText(this@MainActivity,"Total is : $total",Toast.LENGTH_SHORT).show()
//            Log.i("MyTag", "Total is : $total")
        }


        // this functionality relates to structured concurrency using DataManager class with coroutine scope interface.
        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Main).launch {
                tvUserMessage.text = UserDataManager().getTotalUsreCount().toString()
            }
        }


        // for async parallel decomposition with await to get deffered value
//        CoroutineScope(IO).launch {
//            Log.i("MyTag", "Calculation Started")
//            // This is not a parallel decomposition it is sequential.  first function execute and wait for 10 second &
//            // return it s stock value , after return value function 2 will run wait for 8 sec and after that return its stock value
//            val stock1 = async { getStockCount1() }    // in async we need to invoke await function of both async builder
//            val stock2 = async { getStockCount2() }
//            val total = stock1.await() + stock2.await()
//            Log.i("MyTag", "Total is : $total")
//        }
    }

    /**
     * So The best practice for structured concurrency is when you have more than one coroutine , you should always starts with
     * Dispatcher.Main and you should always start with CoroutineScope interface and inside suspended functions you should use coroutineScope
     * function which start with the simple 'c' letter to provide the child scope , this concurrency guarantees to complete all the work
     * started by coroutine with in the child scope before the return of the suspended function return to the caller.
     * other benefit of this structured concurrency when error happens, exception throws structured concurrency guarantees to notify the caller
     * so we can handle error easily and effectively .
     * we can also use structured concurrency to cancel task we started .. if we cancel entire child scope all the work happening in the child
     * scope will be cancelled
     * we can also cancel coroutine separately.
     */
    // functions for dispatcher parallel execution also
    private suspend fun getStockCount1(): Int {
        delay(10000)
        Log.i("MyTag ", "stock 1 returned")
        return 55000
    }

    private suspend fun getStockCount2(): Int {
        delay(8000)
        Log.i("MyTag ", "stock 2 returned")
        return 35000
    }


        // function for async and await
//    private suspend fun getStockCount1(): Int {
//        delay(10000)
//        Log.i("MyTag ", "stock 1 returned")
//        return 55000
//    }
//
//    private suspend fun getStockCount2(): Int {
//        delay(8000)
//        Log.i("MyTag ", "stock 2 returned")
//        return 35000
//    }
}