package com.learning.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {
            // This dispatcher.IO is used with coroutine to shift
            //  this long running task to a separate back ground thread.
            // this launch keyword is a coroutine builder, these are the
            // extension of coroutine which is used to launch new co routine
            // there are 4 main co routine builder
            // Launch  ==> launching a new coroutine with out blocking the main or current thread, returns an instance of job ,
            // which can be used as a reference to the co routine , we use this builder for the co routine which
            // does not have any computational result as the return value but we can use that return job instance to keep track
            // of the co routine & to cancel the coroutine, can not use this coroutine launch to calculate something and get the
            // final answer as the returned value...


            // Async  ==> for return value type result we have to use async coroutine builder, it allows us to launch co routines in parallel
            // it also launch the coroutine without blocking the main thread it returns an instance of deffered of type of result
            // actually deffered interface is an extension of Job interface, we can also use it like a job as cnacelling the
            // coroutine , we use this builder for corotuine that returns some result value,
            // to get the value from deffered object we need to invokes its await() function. launch and Async are commonly used
            //  builder


            // Produce ==>  this builder is used to coroutine which produces a stream of elements return an instance of receivechannel.


            // RunBlocking ==>  in android development we use the co routine we create using this thread will block the thread untill co routine is
            // executing. This is mostly use for testing purpose and returns a result which can be directlly use.


            // Structured Concurrency ===> it is set of language feature and best practice introduces for kotlin Coroutine to avoid leaks and to manage
            //                             them productively


            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }

        }
    }

    private suspend fun downloadUserData() {
        // Using with context function we can easily switch coroutine from one thread to other let us ee
        for (i in 1..50000) {
//            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            // this is a suspended function and we can not call a suspended function from normal function so make this
                // function using modifier suspend
            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }

        }
    }
}
