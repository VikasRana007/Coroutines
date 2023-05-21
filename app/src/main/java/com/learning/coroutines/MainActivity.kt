package com.learning.coroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btnCount.setOnClickListener {
//            tvCount.text = count++.toString()
//        }
//        btnDownloadUserData.setOnClickListener {
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


            // Produce ==>  this builder is used to coroutine which produces a stream of elements return an instance of receive-channel.


            // RunBlocking ==>  in android development we use the co routine we create using this thread will block the thread until co routine is
            // executing. This is mostly use for testing purpose and returns a result which can be directly use.


            // Structured Concurrency ===> it is set of language feature and best practice introduces for kotlin Coroutine to avoid leaks and to manage
            //                             them productively


            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }





//    Now, what if we want to make the network calls in parallel? We can do using async.
//        launch {
//            try {
//                val usersDeferred = async {  getUsers() }
//                val moreUsersDeferred = async { getMoreUsers() }
//                val users = usersDeferred.await()
//                val moreUsers = moreUsersDeferred.await()
//            } catch (exception: Exception) {
//                Log.d(TAG, "$exception handled !")
//            }
//        }

      //  Here, we will face one problem, if any network error comes, the application will crash!,
    //  it will NOT go to the catch block. To solve this, we will have to use the coroutineScope as below:

        //        launch {
//            try {
//                 coroutineScope {
//            val usersDeferred = async {  getUsers() }
//            val moreUsersDeferred = async { getMoreUsers() }
//            val users = usersDeferred.await()
//            val moreUsers = moreUsersDeferred.await()
//        }
//            } catch (exception: Exception) {
//                Log.d(TAG, "$exception handled !")
//            }
//        }

   //     But suppose again, we want to return an empty list for the network call which has failed and continue with
    //     the response from the other network call. We will have to use the supervisorScope and add the try-catch block
    //     to the individual network call as below:

//        launch {
//            supervisorScope {
//                val usersDeferred = async { getUsers() }
//                val moreUsersDeferred = async { getMoreUsers() }
//                val users = try {
//                    usersDeferred.await()
//                } catch (e: Exception) {
//                    emptyList<User>()
//                }
//                val moreUsers = try {
//                    moreUsersDeferred.await()
//                } catch (e: Exception) {
//                    emptyList<User>()
//                }
//            }
//        }

//        }
    }

    private suspend fun downloadUserData() {
        // Using with context function we can easily switch coroutine from one thread to other let us ee
        for (i in 1..50000) {
//            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            // this is a suspended function and we can not call a suspended function from normal function so make this
                // function using modifier suspend
            withContext(Dispatchers.Main) {
//                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
// we can use co routine exception handler to handle the code exception as like below
            val handler = CoroutineExceptionHandler{
                _, exception ->
                Log.d("TAG", exception.toString())
            }

//            just past this handler object var using + operator with the Dispatcher

//            i.e
            GlobalScope.launch(Dispatchers.Main + handler) {
                downloadUserData() // do on IO thread and back to UI Thread
            }
        }
    }





}
