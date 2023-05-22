package com.learning.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class CoroutineCancellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_cancellation)

       GlobalScope.launch {
            execute()
        }

    }


    private suspend fun execute(){
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1.. 999){
                if(isActive) {
                    executeLongRunningTask()
                    println("Value : $i")
                }
            }
        }

        delay(50)
        println("Cancelling the job . . .")
        parentJob.cancel()
        parentJob.join()
        println("Parent job Completed.")
    }


    private fun executeLongRunningTask() {
        for (i in 1.. 1000000){
        }
    }
}