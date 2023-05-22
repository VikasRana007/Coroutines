package com.learning.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class WithContextAndRunBlockingActivity : AppCompatActivity() {
    private val TAG = "KOTLIN FUN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_context_and_run_blocking)

//        GlobalScope.launch {
////            executeTask()
//            executeTaskWithoutWithContext()
//        }

        runBlocking {                  // run blocking actually doing the thing is
                                                          // execution flow step by step in sequence
            launch {
                delay(1000)
                Log.i(TAG, "World")
            }
            Log.i(TAG, "Hello")
        }
    }

    private suspend fun executeTask() {
       Log.d(TAG, "Before")
        withContext(Dispatchers.IO){  // with context is a suspending function that
                                                         // blocks the execution flow till the task will complete
                                                         // this also helps in context switching of coroutine in the inner blocking or else where
            delay(1000)
            Log.d(TAG, "Inside")
        }
        Log.d(TAG, "After")
    }

    private suspend fun executeTaskWithoutWithContext() {
        Log.d(TAG, "Before")
        GlobalScope.launch {
            delay(1000)
            Log.d(TAG, "Inside")
        }
        Log.d(TAG, "After")
    }
}