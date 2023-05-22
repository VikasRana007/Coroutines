package com.learning.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JobHierarcyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_hierarcy)

        /**
         * Here we understand that we can launch multiple coroutine inside a coroutine
         */
        GlobalScope.launch {
            execute()
            executeWithContext()
        }


    }


    private suspend fun execute() {
      val parentJob =  GlobalScope.launch(Dispatchers.Main) {
          println("Parent Coroutine Context  : $coroutineContext")

         val childJob = launch {
             println("Child Coroutine Context  : $coroutineContext")
         }
      }
    }

    private suspend fun executeWithContext() {
        val parentJob =  GlobalScope.launch(Dispatchers.Main) {
            println("Parent Coroutine Context  : $coroutineContext")

            val childJob = launch(Dispatchers.IO) {
                println("Child Coroutine Context  : $coroutineContext")
            }
        }
    }


    // Now you can under stand from above example we can run the child coroutine on the parent coroutine
// and also can make it on separate context
}