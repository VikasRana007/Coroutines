package com.learning.asyncawaitproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // for dispatcher parallel decomposition using with async and await to get data from different sources and combined the result
        CoroutineScope(Main).launch {
            Log.i("MyTag", "Calculation Started")
            // This is not a parallel decomposition it is sequential.  first function execute and wait for 10 second &
            // return it s stock value , after return value function 2 will run wait for 8 sec and after that return its stock value
            val stock1 = async(IO) { getStockCount1() }    // in async we need to invoke await function of both async builder
            val stock2 = async(IO) { getStockCount2() }
            val total = stock1.await() + stock2.await()
            Toast.makeText(this@MainActivity,"Total is : $total",Toast.LENGTH_SHORT).show()
//            Log.i("MyTag", "Total is : $total")
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